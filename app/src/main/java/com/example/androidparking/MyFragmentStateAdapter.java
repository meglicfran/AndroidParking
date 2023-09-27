package com.example.androidparking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    public MyFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new LegacyFragment();
        }else if(position==1){
            return new CarmentEventsFragment();
        }else if(position==2){
            return new OpazajiFragment();
        }else if(position==3){
            return new NaloziFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
