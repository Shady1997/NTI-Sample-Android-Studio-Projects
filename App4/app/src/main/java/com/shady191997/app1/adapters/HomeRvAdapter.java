package com.shady191997.app1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shady191997.app1.R;
import com.shady191997.app1.models.GitHubUsersModel;

import java.util.List;

public class HomeRvAdapter extends
        RecyclerView.Adapter<HomeRvAdapter.StudentViewAdapter> {
    private Context mContext;
    private List<GitHubUsersModel> studentList;

    public HomeRvAdapter(Context mContext, List<GitHubUsersModel> studentList) {

        this.mContext = mContext;
        this.studentList = studentList;
    }


    @NonNull
    @Override
    public StudentViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(mContext).inflate(R.layout.home_rv_list, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "Item is clicked", Toast.LENGTH_SHORT).show();
//            }
//
//        });
        return new StudentViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewAdapter holder, int position) {
        GitHubUsersModel model = studentList.get(position);
        holder.tvName.setText(model.getUserName());
        holder.tvAge.setText(model.getUserUrl());


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class StudentViewAdapter extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;

        public StudentViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_user_name);
            tvAge = itemView.findViewById(R.id.tv_url);
        }


    }

}
