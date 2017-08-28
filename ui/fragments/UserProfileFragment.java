package com.example.sameershekhar.hebecollins.activities.ui.fragments;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.events.InternalStorage;
import com.example.sameershekhar.hebecollins.activities.fcm.FetchingUserDataFromServerThread;
import com.example.sameershekhar.hebecollins.activities.fcm.ReadFromInternalStorageThread;
import com.example.sameershekhar.hebecollins.activities.fcm.SingletonVolley;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserInfoObject;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener,ReadFromInternalStorageThread.AsyncResponse {

   private static Context context1;
    private OnFragmentInteractionListener mListener;
    private ImageButton btn_name,btn_number,btn_goal,btn_weight,btn_height,btn_bodyfat,btn_profileImg;

    private static UserInfoObject userInfoObject;
    private static InternalStorage internalStorage;
    private static ImageView clientProfilePic;
    private static TextView clientName;
    private static TextView clientNumber;
    private static TextView clientGoal;
    private static TextView clientWeight;
    private static TextView clientHeight;
    private static TextView clientEmail;
    private static TextView clientBMI;
    private static TextView clientLeanBodyMass;
    private static TextView clientDOB;
    private static TextView clientBodyFat;

    private static Bitmap bitmap;


    public UserProfileFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        ReadFromInternalStorageThread readFromInternalStorageThread=new ReadFromInternalStorageThread(context1,this);
        readFromInternalStorageThread.execute(Constant.USERINFIOBJECTFILENAME);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_name=(ImageButton)view.findViewById(R.id.edit_name);
        btn_name.setOnClickListener(this);
        btn_profileImg=(ImageButton)view.findViewById(R.id.edit_profile_pic);
        btn_profileImg.setOnClickListener(this);
        btn_number=(ImageButton)view.findViewById(R.id.edit_number);
        btn_number.setOnClickListener(this);
        btn_goal=(ImageButton)view.findViewById(R.id.edit_goal);
        btn_goal.setOnClickListener(this);
        btn_weight=(ImageButton)view.findViewById(R.id.edit_weight);
        btn_weight.setOnClickListener(this);
        btn_height=(ImageButton)view.findViewById(R.id.edit_height);
        btn_height.setOnClickListener(this);
        btn_bodyfat=(ImageButton)view.findViewById(R.id.edit_bodyfat);
        btn_bodyfat.setOnClickListener(this);

        clientName=(TextView)view.findViewById(R.id.Client_name);
        clientNumber=(TextView)view.findViewById(R.id.client_number);
        clientGoal=(TextView)view.findViewById(R.id.client_goal);
        clientWeight=(TextView)view.findViewById(R.id.client_height);
        clientHeight=(TextView)view.findViewById(R.id.client_height);
        clientBodyFat=(TextView)view.findViewById(R.id.client_bodyfat);
        clientProfilePic=(ImageView)view.findViewById(R.id.client_img_profile);

    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        context1=context;
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {


        switch(view.getId())
        {
            case R.id.edit_profile_pic:
                SelectImageFromGallary();
                break;
            case R.id.edit_name:
                getUserInputFromProfilePage(Constant.NAME);
                break;
            case R.id.edit_number:
                getUserInputFromProfilePage(Constant.NUMBER);
                break;
            case R.id.edit_goal:
                getUserInputFromProfilePage(Constant.GOAL);
                break;
            case R.id.edit_weight:
                getUserInputFromProfilePage(Constant.WEIGHT);
                break;
            case R.id.edit_height:
                getUserInputFromProfilePage(Constant.HEIGHT);
                break;
            case R.id.edit_bodyfat:
                getUserInputFromProfilePage(Constant.BODYFAT);
                break;
        }

    }

    public static void getUserInputFromProfilePage(final String title) {


        LayoutInflater li = LayoutInflater.from(context1);
        View promptsView = li.inflate(R.layout.popup_input, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context1);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.UserInput);
        final TextView Title=(TextView)promptsView.findViewById(R.id.title);
        Title.setText("Enter your "+title);


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(title.equals(Constant.NAME))
                                {
                                    clientName.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);


                                }
                                else if(title.equals(Constant.HEIGHT))
                                {
                                    clientHeight.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);

                                }

                                else if(title.equals(Constant.WEIGHT))
                                {
                                    clientWeight.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);
                                    //insert updated name to internal storage

                                }

                                else if(title.equals(Constant.NUMBER))
                                {
                                    clientNumber.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);
                                    //insert updated name to internal storage

                                }
                                else if(title.equals(Constant.BODYFAT))
                                {
                                    clientBodyFat.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);
                                    //insert updated name to internal storage
                                }
                                else if(title.equals(Constant.GOAL))
                                {
                                    clientGoal.setText(userInput.getText().toString());
                                    internalStorage.runTherad(title,userInput.getText().toString(),context1);
                                    //insert updated name to internal storage
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }


    public void SelectImageFromGallary()
    {
           Intent intent=new Intent();
           intent.setType("image/*");
           intent.setAction(Intent.ACTION_GET_CONTENT);
           startActivityForResult(intent,Constant.IMG_REQUEST);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Constant.IMG_REQUEST && resultCode==RESULT_OK&& data!=null)
        {
          Uri path=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(context1.getContentResolver(),path);
                clientProfilePic.setImageBitmap(bitmap);
                uploadImageToServer(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private static void uploadImageToServer(final Bitmap bitmap)
    {

        StringRequest stringrequest=new StringRequest(Request.Method.POST, Config.profile_img_upload_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject json=new JSONObject(response);
                    String res=json.getString("response");
                    Toast.makeText(context1,res,Toast.LENGTH_LONG).show();
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
                Map<String,String> map=new HashMap<>();
                map.put("name","xyz");
                map.put("image",imageToString(bitmap));

                return map;
            }
        };

        SingletonVolley.getInstance(context1).addTorequestQueue(stringrequest);


    }

   private static String imageToString(Bitmap bitmap)
   {
       ByteArrayOutputStream  byteArrayOutputStream=new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
       byte[] imgBytes=byteArrayOutputStream.toByteArray();
       return Base64.encodeToString(imgBytes,Base64.DEFAULT);


   }

    @Override
    public void processFinish(Object object) {

    }


    private static void setAllTextView(UserInfoObject userInfoObject)
    {
        clientName.setText(userInfoObject.getUserName());
        clientEmail.setText(userInfoObject.getUserEmailId());
        clientNumber.setText(userInfoObject.getUserContactNumber());
        clientDOB.setText(userInfoObject.getUserDOB());
        clientGoal.setText(userInfoObject.getUserGoal());
        clientHeight.setText(userInfoObject.getUserHeight());
        clientWeight.setText(userInfoObject.getUserWeight());
        clientBMI.setText(userInfoObject.getUserBMI());
        clientLeanBodyMass.setText(userInfoObject.getUserLeanBodyMass());
        clientBodyFat.setText(userInfoObject.getUserBodyfat());

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
