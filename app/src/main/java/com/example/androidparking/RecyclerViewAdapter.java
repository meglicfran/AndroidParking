package com.example.androidparking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class RecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    ViewPager2 pager;

    FragmentManager fragmentManager;

    public RecyclerViewAdapter(ViewPager2 pager, FragmentManager fragmentManager) {
        super();
        this.fragmentManager = fragmentManager;
        this.pager=pager;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.pager=pager;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_card,parent,false);
        return new EventViewHolder(view,2,1);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.uredi.setOnClickListener(view -> {
            pager.setCurrentItem(0);

            LegacyFragment legacyFragment = (LegacyFragment) fragmentManager.findFragmentByTag("f0");
            legacyFragment.plateEditText.setText(holder.plate.getText().toString());

            legacyFragment.stranaRegCheckBox.setChecked(true);
            legacyFragment.countryDropdownTextView.setText(legacyFragment.countryDropdownTextView.getAdapter().getItem(holder.countryIndex).toString(),false);
            legacyFragment.parkingDropdownTextView.setText(legacyFragment.parkingDropdownTextView.getAdapter().getItem(holder.parkingIndex).toString(),false);


        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
