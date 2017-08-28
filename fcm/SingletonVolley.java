package com.example.sameershekhar.hebecollins.activities.fcm;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sameershekhar on 17-Aug-17.
 */

public class SingletonVolley {

    private static SingletonVolley singletonVolley;
    private static Context ctx;
    private static RequestQueue requestQueue;

    private SingletonVolley(Context ctx) {
        this.ctx=ctx;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(ctx);
        }

        return requestQueue;
    }

    public static synchronized SingletonVolley getInstance(Context context)
    {
        if(singletonVolley==null) {
            singletonVolley = new SingletonVolley(context);
        }
        return singletonVolley;
    }
    public <T> void addTorequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}
