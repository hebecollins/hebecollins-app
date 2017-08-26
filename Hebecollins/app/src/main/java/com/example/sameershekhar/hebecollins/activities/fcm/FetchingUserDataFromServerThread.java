package com.example.sameershekhar.hebecollins.activities.fcm;

import android.content.Context;
import android.service.voice.VoiceInteractionSession;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sameershekhar.hebecollins.activities.events.InternalStorage;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserInfoObject;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sameershekhar on 23-Aug-17.
 */

public class FetchingUserDataFromServerThread {

    public FetchingUserDataFromServerThread() {
    }

    public static void fetchDataFromServerTherad(final String userId,final Context context)
    {
        //fetch all the data from server and store in internal storage
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.profile_data_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UserInfoObject userInfoObject;

                        try {
                            JSONObject jsonObject=new JSONObject(response);

                            Boolean error=jsonObject.getBoolean("response");
                            if(!error)
                            {
                                //String uid = jObj.getString("uid");

                                JSONObject user = jsonObject.getJSONObject("user");
                                String userName = user.getString("userName");
                                String userEmail = user.getString("userEmail");
                                String userContact = user.getString("userContact");
                                String goal = user.getString("goal");
                                String dob = user.getString("dob");
                                String weight = user.getString("weight");
                                String height = user.getString("height");
                                String bodyFat = user.getString("bodyFat");
                                String bmi = user.getString("bmi");
                                String leanBodyMass = user.getString("leanBodyMass");
                                String trainerName = user.getString("trainerName");
                                String trainerAge = user.getString("trainerAge");
                                String trainerNumber=user.getString("trinaerNumber");
                                String trainerCertification = user.getString("trainerCertification");
                                String trainerAchievments = user.getString("trainerAchievments");
                                userInfoObject=new UserInfoObject(userName,userEmail,userContact,goal,
                                        dob,weight,height,bodyFat,bmi,leanBodyMass,trainerName,trainerAge,trainerNumber,trainerCertification
                                ,trainerAchievments);
                                try {
                                    InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }




                            // make a userInfoObject and Store that object in internal storage






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("user_id",userId);

                        return params;
                    }
                };
              SingletonVolley.getInstance(context).addTorequestQueue(stringRequest);

            }
}


