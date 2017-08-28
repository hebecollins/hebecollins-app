package com.example.sameershekhar.hebecollins.activities.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.adapters.UserDayWorkoutAdapter;
import com.example.sameershekhar.hebecollins.activities.fcm.ReadFromInternalStorageThread;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserSingleWorkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserProfileHomeFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UserProfileHomeFrag extends Fragment implements ReadFromInternalStorageThread.AsyncResponse {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    public UserProfileHomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_profile_home, container, false);
        //check today workout in the internal storage of week workout on that particular day
        //we have seven internal file for seven week workout days


         ReadFromInternalStorageThread readFromInternalStorageThread=new ReadFromInternalStorageThread(getContext(),this);
         readFromInternalStorageThread.execute(Constant.USERWORKOUTFILENAME);
        recyclerView=(RecyclerView)view.findViewById(R.id.recylerView_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setHasFixedSize(true);


        return view;

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

    @Override
    public void processFinish(Object object) {

        Map<String,ArrayList<UserSingleWorkout>> map=(HashMap)object;
        ArrayList<UserSingleWorkout> arrayList=new ArrayList();//map.get("Current Day");

       arrayList.add(new UserSingleWorkout("Lateral","12-15","3","30 seconds"));
        arrayList.add(new UserSingleWorkout("Front Raises","12-15","3","30 seconds"));
        arrayList.add(new UserSingleWorkout("Squats","15","3","1 minutes"));
        arrayList.add(new UserSingleWorkout("Ankle Crunches","30","3","10 seconds"));
        UserDayWorkoutAdapter userDayWorkoutAdapter=new UserDayWorkoutAdapter(arrayList,getContext());
        recyclerView.setAdapter(userDayWorkoutAdapter);
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
