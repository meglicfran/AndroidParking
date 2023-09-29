package com.example.androidparking;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    ViewPager2 pager;

    FragmentManager fragmentManager;

    ArrayList<EventModel> eventModels;

    public RecyclerViewAdapter(ViewPager2 pager, FragmentManager fragmentManager, ArrayList<EventModel> eventModels) {
        super();
        this.fragmentManager = fragmentManager;
        this.pager=pager;
        this.eventModels = eventModels;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_card,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventModels.get(position);

        holder.plate.setText(event.getPlate());
        holder.time.setText(event.getDateTime());
        //holder.image.setImageURI(Uri.fromFile(new File(event.getFilename())));

        holder.uredi.setOnClickListener(view -> {
            pager.setCurrentItem(0);

            LegacyFragment legacyFragment = (LegacyFragment) fragmentManager.findFragmentByTag("f0");

            legacyFragment.plateEditText.setText(event.getPlate());
            legacyFragment.stranaRegCheckBox.setChecked(true);
            legacyFragment.countryDropdownTextView.setText(legacyFragment.countryDropdownTextView.getAdapter().getItem(event.getCountryIndex()).toString(),false);
            legacyFragment.parkingDropdownTextView.setText(legacyFragment.parkingDropdownTextView.getAdapter().getItem(event.getCountryIndex()).toString(),false);
        });

    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }
}
