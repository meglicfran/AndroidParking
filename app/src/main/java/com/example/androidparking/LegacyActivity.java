package com.example.androidparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LegacyActivity extends AppCompatActivity {
    ViewPager2 pager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legacy);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentStateAdapter adapter = new MyFragmentStateAdapter(getSupportFragmentManager(),getLifecycle(),pager);
        pager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, pager, (tab, position) -> {
            if(position==0){
                tab.setText("Legacy");
            }else if(position==1){
                tab.setText("Carmen events");
            }else if(position==2){
                tab.setText("Opažaji");
            }else if(position==3){
                tab.setText("Nalozi");
            }
        }).attach();


        //replaceFragment(new LegacyFragment(),false);


    }

    /*
    private void replaceFragment(Fragment fragment,boolean addToBackStack){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

     */
}