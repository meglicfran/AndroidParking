package com.example.androidparking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    ViewPager2 pager;
    public MyFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ViewPager2 pager) {
        super(fragmentManager, lifecycle);
        this.pager=pager;
    }

    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new LegacyFragment();
        }else if(position==1){
            return new CarmenEventsFragment(pager);
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
