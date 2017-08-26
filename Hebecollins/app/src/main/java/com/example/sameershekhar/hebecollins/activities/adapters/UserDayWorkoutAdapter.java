package com.example.sameershekhar.hebecollins.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.models.UserSingleWorkout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sameershekhar on 21-Aug-17.
 */

public class UserDayWorkoutAdapter extends RecyclerView.Adapter<UserDayWorkoutAdapter.MyViewHolder>{

    ArrayList<UserSingleWorkout> userSingleWorkoutArrayList=new ArrayList<>();
    Activity activity;

    public UserDayWorkoutAdapter(ArrayList<UserSingleWorkout> userSingleWorkoutArrayList, Context context) {
        this.userSingleWorkoutArrayList=userSingleWorkoutArrayList;
        this.activity=(Activity)context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_for_user_home_rv,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.exercise_name.setText(userSingleWorkoutArrayList.get(position).getExerciseName());

        String xyz = userSingleWorkoutArrayList.get(position).getExerciseReps();
        String next = "<font color='#F44336'>"+xyz+"</font>";
        holder.exercise_reps.setText(Html.fromHtml("reps: "+ next));

        xyz = userSingleWorkoutArrayList.get(position).getExreciseSets();
        next = "<font color='#F44336'>"+xyz+"</font>";
        holder.exercise_sets.setText(Html.fromHtml("sets: "+ next));
        xyz = userSingleWorkoutArrayList.get(position).getExerciseRest();
        next = "<font color='#F44336'>"+xyz+"</font>";
        holder.exercise_rest.setText(Html.fromHtml("rest: "+ next));
    }

    @Override
    public int getItemCount() {
        return userSingleWorkoutArrayList.size();
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


