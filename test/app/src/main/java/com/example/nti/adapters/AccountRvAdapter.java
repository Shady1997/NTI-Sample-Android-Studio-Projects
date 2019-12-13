package com.example.nti.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nti.R;
import com.example.nti.models.DoctorModel;

import java.util.ArrayList;
import java.util.List;

public class AccountRvAdapter extends RecyclerView.Adapter<AccountRvAdapter.AccountViewHolder> {


    private Context context;
    private List<String> accountList ;


    public AccountRvAdapter(Context context, List<String> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_settings_account,parent,false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

        String hamada = accountList.get(position);
        holder.tvName.setText(hamada);

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }



    public class AccountViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_doctor_name);
        }
    }
}
