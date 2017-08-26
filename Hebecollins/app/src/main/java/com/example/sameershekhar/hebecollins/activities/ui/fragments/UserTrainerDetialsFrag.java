package com.example.sameershekhar.hebecollins.activities.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.fcm.ReadFromInternalStorageThread;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserInfoObject;


public class UserTrainerDetialsFrag extends Fragment implements ReadFromInternalStorageThread.AsyncResponse {

    private OnFragmentInteractionListener mListener;


    TextView trainerName, trainerAge, trainerNumber, trainerCertification, trainerAchievments;
    private Context context1;


    public UserTrainerDetialsFrag() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_trainer_detials, container, false);

        ReadFromInternalStorageThread readFromInternalStorageThread=new ReadFromInternalStorageThread(context1,this);
        readFromInternalStorageThread.execute(Constant.USERINFIOBJECTFILENAME);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trainerName=(TextView)view.findViewById(R.id.trainer_name);
        trainerAge=(TextView)view.findViewById(R.id.trainer_age);
        trainerNumber=(TextView)view.findViewById(R.id.trainer_number);
        trainerCertification=(TextView)view.findViewById(R.id.trainer_certification);
        trainerAchievments=(TextView)view.findViewById(R.id.trainer_achievments);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context1=context;
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

        UserInfoObject userInfoObject=(UserInfoObject)object;
       /* trainerName.setText(userInfoObject.getUserTrainername());
        trainerNumber.setText(userInfoObject.getUserTrainerNumber());
        trainerCertification.setText(userInfoObject.getUserTrainerCertification());
        trainerAge.setText(userInfoObject.getUserTrainerAge());
        trainerAchievments.setText(userInfoObject.getUserTrainerAchievment());
*/

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
