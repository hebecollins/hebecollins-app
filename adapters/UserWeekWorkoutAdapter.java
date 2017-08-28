package com.example.sameershekhar.hebecollins.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sameershekhar.hebecollins.R;

/**
 * Created by sameershekhar on 19-Aug-17.
 */

public class UserWeekWorkoutAdapter extends RecyclerView.Adapter<UserWeekWorkoutAdapter.MyViewHolder> {

    public UserWeekWorkoutAdapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_for_user_home_rv,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView exercise_name,exercise_reps,exercise_sets,exercise_rest;

        public MyViewHolder(View itemView) {
            super(itemView);

            exercise_name=(TextView)itemView.findViewById(R.id.exercise_name);
            exercise_reps=(TextView)itemView.findViewById(R.id.exercise_reps);
            exercise_sets=(TextView)itemView.findViewById(R.id.exercise_sets);
            exercise_rest=(TextView)itemView.findViewById(R.id.exercise_rest);

        }
    }


}
