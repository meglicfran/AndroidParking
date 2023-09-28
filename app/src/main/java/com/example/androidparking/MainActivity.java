package com.example.androidparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    TextInputLayout usernameInputLayout, passwordInputLayout;
    TextInputEditText usernameEditText, passwordEditText;
    Button logInBtn,carmenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        logInBtn = findViewById(R.id.loginButton);
        carmenBtn = findViewById(R.id.carmenButton);

        logInBtn.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(!authenticate(username,password)){
                return;
            }
            Intent i = new Intent(getApplicationContext(),LegacyActivity.class);
            startActivity(i);
        });

        carmenBtn.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if(!authenticate(username,password)){
                return;
            }
            startVideoProcessing();
        });

    }
    private boolean authenticate(String username, String password){
        usernameInputLayout.setError("");
        passwordInputLayout.setError("");

        //valid username
        if(username.equals("")){
            usernameInputLayout.setError("Username can't be empty");
            return  false;
        }

        //valid password
        if(password.equals("")){
            passwordInputLayout.setError("Password can't be empty");
            return false;
        }


        usernameInputLayout.setEnabled(false);
        passwordInputLayout.setEnabled(false);
        //authenticate
        //passwordInputLayout.setError("Invalid username and password combination");

        usernameInputLayout.setEnabled(true);
        passwordInputLayout.setEnabled(true);

        return true;
    }

    private void startVideoProcessing() {
        JSONObject settings = new JSONObject();
        try {
            settings.put("startVideo", true);
            settings.put("mode", "Custom");
            settings.put("receiverClass", "com.example.androidparking.CarmenResultReceiver");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.arh.anprclientwithopencv");
        if (launchIntent != null) {
            launchIntent.putExtra("settings", settings.toString());
            startActivity(launchIntent);
        }
    }
}