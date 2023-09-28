package com.example.androidparking;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    TextView plate, time;
    ImageView image;

    Button uredi,nalog;
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        this.plate = itemView.findViewById(R.id.plate);
        this.time = itemView.findViewById(R.id.time);
        this.image = itemView.findViewById(R.id.image);
        this.uredi = itemView.findViewById(R.id.uredi);
        this.nalog = itemView.findViewById(R.id.nalog);
    }
}
