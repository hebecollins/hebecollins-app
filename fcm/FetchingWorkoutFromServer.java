package com.example.sameershekhar.hebecollins.activities.fcm;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sameershekhar.hebecollins.activities.events.InternalStorage;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserSingleWorkout;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sameershekhar on 23-Aug-17.
 */

public class FetchingWorkoutFromServer {


    static Context context;

    public FetchingWorkoutFromServer(Context context) {

        this.context = context;
    }

    public static void fetchindWorkoutDataFromServer(final String trainerId, final String clientId,final Context contex)
    {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.user_week_workout_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Map<String ,List<UserSingleWorkout>> weekWorkoutMap=new HashMap<>();

                try {
                    JSONObject jsonObject=new JSONObject(response);


                    boolean error=jsonObject.getBoolean("response");
                    JSONObject workout=jsonObject.getJSONObject("workout");
                    if(!error)
                    {

                        List<UserSingleWorkout> list=new ArrayList<>();
                        JSONArray dayWorkout = workout.getJSONArray("Sunday");
                        int count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Sunday",list);
                        list.clear();

                         dayWorkout = workout.getJSONArray("Monday");
                        count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Monday",list);
                        list.clear();


                        dayWorkout = workout.getJSONArray("Tuesday");
                        count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Tuesday",list);
                        list.clear();
                      dayWorkout = workout.getJSONArray("Wednesday");
                         count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Wednesday",list);
                        list.clear();

                        dayWorkout = workout.getJSONArray("Thursday");
                        count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Thursday",list);
                        list.clear();

                        dayWorkout = workout.getJSONArray("Friday");
                       count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Friday",list);
                        list.clear();

                        dayWorkout = workout.getJSONArray("Saturday");
                         count=0;
                        while (count!=dayWorkout.length())
                        {
                            JSONObject work= (JSONObject) dayWorkout.get(count);
                            String exerciseName=work.getString("exerciseName");
                            String sets=work.getString("set");
                            String reps=work.getString("reps");
                            String rest=work.getString("rest");
                            UserSingleWorkout usersingleWorkout=new UserSingleWorkout(exerciseName,sets,reps,rest);
                            list.add(usersingleWorkout);
                            count++;
                        }
                        weekWorkoutMap.put("Saturday",list);
                        list.clear();

                        try {

                            InternalStorage.writeObject(context,Constant.USERWORKOUTFILENAME,weekWorkoutMap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //get all the week workout here and create  week workout based on (day+array of usersinglewrokout)
                //create in differnt file based on date name



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parmas=new HashMap<>();
                parmas.put("Trainer_id",trainerId);
                parmas.put("Client_id",clientId);
                return parmas;
            }
        };


    }
}
