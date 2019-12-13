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
import com.example.nti.adapters.AccountRvAdapter;
import com.example.nti.adapters.HomeRvAdapter;
import com.example.nti.models.DoctorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSettingsFragment extends Fragment {

    RecyclerView accountRecyclerView;


    public AccountSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_account_settings, container, false);

        List<String> hamadas = new ArrayList<>();
        String doc = "7amada yel3ab";
        hamadas.add(doc);hamadas.add(doc);hamadas.add(doc);hamadas.add(doc);hamadas.add(doc);hamadas.add(doc);hamadas.add(doc);
        myRecyclerView(view,hamadas);

        return view;
    }

    private void myRecyclerView(View view, List<String> hamadaList) {

        accountRecyclerView = view.findViewById(R.id.rv_settings_account);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(accountRecyclerView.getContext(),
                layoutManager.getOrientation());

        AccountRvAdapter rvAdapter = new AccountRvAdapter(getContext(),hamadaList);
        accountRecyclerView.addItemDecoration(dividerItemDecoration);
        accountRecyclerView.setItemAnimator(new DefaultItemAnimator());
        accountRecyclerView.setLayoutManager(layoutManager);
        accountRecyclerView.setAdapter(rvAdapter);
    }

}
