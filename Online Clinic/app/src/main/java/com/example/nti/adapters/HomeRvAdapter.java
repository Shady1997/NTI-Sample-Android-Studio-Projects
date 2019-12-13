package com.example.nti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nti.R;
import com.example.nti.models.DoctorModel;

import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.DoctorViewHolder> {


    private Context context;
    private List<DoctorModel> doctorsList;
    private RecyclerView mRecyclerView;


    public HomeRvAdapter(Context context, List<DoctorModel> doctorsList, RecyclerView mRecyclerView) {
        this.context = context;
        this.doctorsList = doctorsList;
        this.mRecyclerView = mRecyclerView;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_rv_items,parent,false);
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(v);
                Toast.makeText(context, String.valueOf(itemPosition), Toast.LENGTH_SHORT).show();
            }
        });

        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorModel doctor = doctorsList.get(position);
        holder.dName.setText(doctor.getName());
        holder.dSpecialization.setText(doctor.getSpecialization());
        holder.dnumber.setText(doctor.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        TextView dName;
        TextView dSpecialization;
        TextView dnumber;


        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            dName = itemView.findViewById(R.id.tv_doctor_name);
            dSpecialization = itemView.findViewById(R.id.tv_doctor_specialization);
            dnumber = itemView.findViewById(R.id.tv_doctor_number);
        }
    }

}
