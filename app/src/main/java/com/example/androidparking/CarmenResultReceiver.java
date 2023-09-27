package com.example.androidparking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

public class CarmenResultReceiver extends BroadcastReceiver {
    private static final String TAG = "Receiver";
    private Context context;
    public CarmenResultReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Broadcast Received!");

        this.context = context;

        final BroadcastReceiver.PendingResult pendingResult = goAsync();
        Task asyncTask = new Task(pendingResult, intent, context);
        asyncTask.execute();
    }
    private static class Task extends AsyncTask<String, Integer, String> {
        private final BroadcastReceiver.PendingResult pendingResult;
        private final Intent intent;
        private final Context context;
        private Task(BroadcastReceiver.PendingResult pendingResult, Intent intent, Context context) {
            this.pendingResult = pendingResult;
            this.intent = intent;
            this.context = context;
        }
        @Override
        protected String doInBackground(String... strings) {
            if(intent.getAction() != null)
            {
                String JSONResultString = intent.getStringExtra("event");
                //Log.i(TAG, JSONResult);
                /*
                String plate, fileName;
                long timeStamp;
                try {
                    JSONObject JSONResultObj = new JSONObject(JSONResultString);
                    JSONObject JSONPlateObj = JSONResultObj.getJSONObject("plate");
                    plate = JSONPlateObj.getString("unicodeText");
                    timeStamp = JSONResultObj.getLong("timestamp");
                    fileName = JSONResultObj.getString("fileName");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                Timestamp tms =  new Timestamp(timeStamp);
                Date date = new Date(tms.getTime());
                Log.i(TAG,"Plate: "+plate);
                Log.i(TAG,"Date: "+date);
                Log.i(TAG,"File Name: "+fileName);
                 */

                DatabaseHelper dbHelper = new DatabaseHelper(context);

                if(dbHelper.eventsInsertJSON(JSONResultString)) {
                    Log.i(TAG, "JSON stored in table");
                }else {
                    Log.i(TAG,"Error storing json in table");
                }
                syncData();
            }
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pendingResult.finish();
        }

        void syncData(){
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor cursor = databaseHelper.eventsGetUnsyncedRows();
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String JSON = cursor.getString(1);
               /*
                RowSender.SendRow(id, JSON, context, (id2, status) -> {
                    if(status){
                        Log.i(TAG, "Row " + id2 + " sync successful");
                    }else{
                        Log.i(TAG, "Row " + id2 + " error while syncing");
                    }
                });
                */

            }
            return;
        }
    }
}
