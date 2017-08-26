package com.example.sameershekhar.hebecollins.activities.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.adapters.UserImageGallaryAdaptor;
import com.example.sameershekhar.hebecollins.activities.models.UserAlbum;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserPhotoGallaryFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UserPhotoGallaryFrag extends Fragment {

    ArrayList<UserAlbum> userAlbumArrayList=new ArrayList<>();
    RecyclerView recyclerView;
    UserImageGallaryAdaptor userImageGallaryAdaptor;

    private OnFragmentInteractionListener mListener;

    public UserPhotoGallaryFrag() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView=(RecyclerView)getView().findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, Config.Image_gallry_server_path,  null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int count=0;
                        while(count<response.length())
                        {
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                userAlbumArrayList.add(new UserAlbum(jsonObject.getInt("client_id"),jsonObject.getString("image_name"),"xyz"));

                                //Toast.makeText(UserPhotoGallary.this,userAlbumArrayList.get(count).getImage_name(),Toast.LENGTH_LONG).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            count++;
                        }

                        userImageGallaryAdaptor=new UserImageGallaryAdaptor(userAlbumArrayList,getContext());
                        recyclerView.setAdapter(userImageGallaryAdaptor);


                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        // SingletonVolley.getInstance(this).addTorequestQueue(jsonArrayRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_photo_gallary, container, false);


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
