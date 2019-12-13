package com.example.nti.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nti.R;
import com.example.nti.models.GithubUsersModel;

import java.util.List;

// Step 1
public class HomeRvAdapter
        extends RecyclerView.Adapter<HomeRvAdapter.StudentViewHolder> {
    //Step 3
//    private Context mContext;
    private List<GithubUsersModel> studentsList;

    public HomeRvAdapter(List<GithubUsersModel> studentsList) {
//        this.mContext = mContext;
        this.studentsList = studentsList;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        //Step 5

        final View view =
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.home_rv_items, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(view.getContext(), "Position: " +), Toast.LENGTH_SHORT).show();
//            }
//        });
        return new StudentViewHolder(view);
    }

    //Step 6
    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, final int position) {
        GithubUsersModel model = studentsList.get(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(this, "Position: ",Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.tvName.setText(model.getUserName());
        holder.tvAge.setText(model.getUserUrl());
        holder.tvId.setText(model.getId());

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    // Step 2
    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;
        TextView tvId;

        // Step 4
        public StudentViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvId = itemView.findViewById(R.id.tv_id);

            // item is clickable and toast is shown
            itemView.setClickable(true);
            itemView.setFocusableInTouchMode(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), "Position: " + Integer.toString(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}
