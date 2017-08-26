package com.example.sameershekhar.hebecollins.activities.events;

import android.content.Context;

import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.models.UserInfoObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Handler;

/**
 * Created by sameershekhar on 19-Aug-17.
 */

public final class InternalStorage {

    private static InternalStorage internalStorage;
    private InternalStorage() {}

    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    public static void runTherad(final String title, final String vlaue, final Context context)
    {


// this method updating file at the time of user edit
       //internalStorage=InternalStorage.getInternalStorageInstance();
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                // your code here
                if(title.equals(Constant.NAME))
                {
                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserName(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
               else if(title.equals(Constant.NUMBER))
                {
                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserContactNumber(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if(title.equals(Constant.GOAL))
                {
                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserGoal(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                else if(title.equals(Constant.HEIGHT))
                {

                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserHeight(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                else if(title.equals(Constant.WEIGHT))
                {

                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserWeight(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                else if(title.equals(Constant.BODYFAT))
                {
                    try {
                        UserInfoObject userInfoObject=(UserInfoObject)InternalStorage.readObject(context,Constant.USERINFIOBJECTFILENAME);
                        userInfoObject.setUserBodyfat(vlaue);
                        InternalStorage.writeObject(context,Constant.USERINFIOBJECTFILENAME,userInfoObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                /*handler.post(new Runnable()  //If you want to update the UI, queue the code on the UI thread
                {
                    public void run()
                    {
                        //Code to update the UI
                    }
                });*/
            }
        };

        Thread t = new Thread(r);
        t.start();
    }

    public static InternalStorage getInternalStorageInstance()
    {
        if(internalStorage==null)
            internalStorage=new InternalStorage();

        return internalStorage;

    }

}

/*List<Entry> entries = new ArrayList<Entry>();
entries.add(new Entry("House"));
entries.add(new Entry("Car"));
entries.add(new Entry("Job"));

try {
   // Save the list of entries to internal storage
   InternalStorage.writeObject(this, KEY, entries);

   // Retrieve the list from internal storage
   List<Entry> cachedEntries = (List<Entry>) InternalStorage.readObject(this, KEY);

   // Display the items from the list retrieved.
   for (Entry entry : cachedEntries) {
     Log.d(TAG, entry.getName());
   }
} catch (IOException e) {
   Log.e(TAG, e.getMessage());
} catch (ClassNotFoundException e) {
   Log.e(TAG, e.getMessage());
}*/