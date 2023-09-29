package com.example.androidparking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarmenEventsFragment extends Fragment {

    RecyclerView recyclerView;
    ViewPager2 pager;
    ArrayList<EventModel> eventModels;

    public CarmenEventsFragment(ViewPager2 pager) {
        super();
        this.pager = pager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);

        eventModels = new ArrayList<>();
        testData();

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(pager, getActivity().getSupportFragmentManager(),eventModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void testData(){
        eventModels.add(new EventModel("NSN666","29.9.2023 9:15 - prije 15 min","/img*",0,1));
        eventModels.add(new EventModel("54DMB1","29.9.2023 9:15 - prije 15 min","/img*",1,1));
        eventModels.add(new EventModel("86GZNK","29.9.2023 9:15 - prije 15 min","/img*",2,0));
        eventModels.add(new EventModel("NSN666","29.9.2023 9:15 - prije 15 min","/img*",0,2));
        eventModels.add(new EventModel("CK889EO","29.9.2023 9:15 - prije 15 min","/img*",3,1));
        eventModels.add(new EventModel("NSN666","29.9.2023 9:15 - prije 15 min","/img*",0,1));
    }
}