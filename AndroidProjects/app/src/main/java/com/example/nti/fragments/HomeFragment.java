package com.example.nti.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nti.R;
import com.example.nti.activities.HomeActivity;
import com.example.nti.adapters.HomeRvAdapter;
import com.example.nti.models.DoctorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView homeRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        List<DoctorModel> doctors = new ArrayList<>();
        DoctorModel doc = new DoctorModel("Abdo","Dentist","01224537789");
        doctors.add(doc);doctors.add(doc);doctors.add(doc);doctors.add(doc);doctors.add(doc);doctors.add(doc);doctors.add(doc);
        myRecyclerView(fragmentView,doctors);
        return fragmentView;
    }


    private void myRecyclerView(View view, List<DoctorModel> doctorList) {
        homeRecyclerView = view.findViewById(R.id.rv_home);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(homeRecyclerView.getContext(),
                layoutManager.getOrientation());

        HomeRvAdapter rvAdapter = new HomeRvAdapter(getContext(), doctorList, homeRecyclerView);

        homeRecyclerView.addItemDecoration(dividerItemDecoration);
        homeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        homeRecyclerView.setLayoutManager(layoutManager);
        homeRecyclerView.setAdapter(rvAdapter);
    }


}
