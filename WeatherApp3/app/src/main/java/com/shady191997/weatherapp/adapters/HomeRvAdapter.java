package com.shady191997.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shady191997.weatherapp.R;
import com.shady191997.weatherapp.model.OpenWeatherModel;
import com.shady191997.weatherapp.model.WeatherModel;

import java.util.List;


public class HomeRvAdapter extends
        RecyclerView.Adapter<HomeRvAdapter.StudentViewAdapter> {
    private Context mContext;
    private List<OpenWeatherModel> studentList;

    public HomeRvAdapter(Context mContext, List<OpenWeatherModel> studentList) {

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
        //OpenWeatherModel model = studentList.get(position);
        //List<List<WeatherModel>> weatherModel=model.getWeather();
        //holder.tvAge.setText(Integer.toString(weatherModel.get(position).get(position).getId()));
        //holder.tvName.setText(weatherModel.get(position).get(position).getDescription());


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
