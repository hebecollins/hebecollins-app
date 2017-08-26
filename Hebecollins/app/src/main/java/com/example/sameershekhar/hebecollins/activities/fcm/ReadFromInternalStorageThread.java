package com.example.sameershekhar.hebecollins.activities.fcm;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sameershekhar.hebecollins.activities.events.InternalStorage;

import java.io.IOException;

/**
 * Created by sameershekhar on 24-Aug-17.
 */

public class ReadFromInternalStorageThread extends AsyncTask<String,Void,Object> {

    Context context;

    public interface AsyncResponse
    {
      void processFinish(Object object);
    }

    public AsyncResponse asyncResponse=null;

    public ReadFromInternalStorageThread(Context context,AsyncResponse asyncResponse) {
        super();
        this.context=context;
        this.asyncResponse=asyncResponse;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(String... strings) {
        String filename=strings[0];
        Object object=null;
        try {

            object=InternalStorage.readObject(context,filename);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return object ;
    }


    @Override
    protected void onPostExecute(Object object) {
        asyncResponse.processFinish(object);

    }
}
