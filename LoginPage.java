package com.example.sameershekhar.hebecollins.activities;

/**
 * Created by sameershekhar on 16-Aug-17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

;import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.fcm.FetchingUserDataFromServerThread;
import com.example.sameershekhar.hebecollins.activities.fcm.FetchingWorkoutFromServer;
import com.example.sameershekhar.hebecollins.activities.fcm.SessionManager;
import com.example.sameershekhar.hebecollins.activities.models.Constant;
import com.example.sameershekhar.hebecollins.activities.fcm.SingletonVolley;
import com.example.sameershekhar.hebecollins.activities.ui.UserProfile;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    SessionManager sessionManager;
    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences,loginLocalCredentialsPreferences;
    private SharedPreferences.Editor loginPrefsEditor,loginLocalCredentialsPreferencesEditor;
    private Boolean saveLogin,saveLoginCredentilas;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        _emailText=(EditText)findViewById(R.id.input_email);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _loginButton=(Button) findViewById(R.id.btn_login);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        loginLocalCredentialsPreferences=getSharedPreferences("loginCredentilas",MODE_PRIVATE);
        loginLocalCredentialsPreferencesEditor=loginLocalCredentialsPreferences.edit();



         sessionManager=new SessionManager(getApplicationContext());

        if(sessionManager.isLoggedIn())
        {

            Intent intent=new Intent(LoginPage.this,UserProfile.class);
            startActivity(intent);
            finish();

        }




        saveLogin = loginPreferences.getBoolean(Constant.SAVElOGIN, false);
        if (saveLogin == true) {
            _emailText.setText(loginPreferences.getString(Constant.USERNAME, ""));
            _passwordText.setText(loginPreferences.getString(Constant.PASSWORD, ""));
            saveLoginCheckBox.setChecked(true);
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale_heading);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.heading);
        tv.clearAnimation();
        tv.startAnimation(a);




    }

    public void login() {
        Log.d(TAG, "Login");

       /* if (!validate()) {
            onLoginFailed();
            return;
        }*/

        _loginButton.setEnabled(false);
        progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


                        // TODO: Implement your own authentication logic here.

        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean(Constant.SAVElOGIN, true);
            loginPrefsEditor.putString(Constant.USERNAME, email);
            loginPrefsEditor.putString(Constant.PASSWORD, password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
        saveLoginCredentilas=loginLocalCredentialsPreferences.getBoolean(Constant.SAVELOGINCREDENTIALS,false);
        if(saveLoginCredentilas==true)
        {
            String username=loginLocalCredentialsPreferences.getString(Constant.USERNAME,"");
            String pass=loginLocalCredentialsPreferences.getString(Constant.PASSWORD,"");

                            if(email.equals(username) && password.equals(pass))
                                onLoginSuccess();
                            else
                            {
                               // Toast.makeText(getBaseContext(), "Login failed logincredentials", Toast.LENGTH_LONG).show();
                                onLoginFailed();
                            }


        }
        else
        {
            //fetch data from server and set preferences
          RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.login_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jObj = null;
                            Log.v("response",response);
                            //JSONParser parser = new JSONParser();
                            //JSONObject json = (JSONObject) parser.parse(stringToParse);
                            Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                           try {

                                jObj = new JSONObject(response);
                               //Toast.makeText(getBaseContext(), jObj.toString(), Toast.LENGTH_LONG).show();
                                Boolean error = jObj.getBoolean("error");
                                if(!error)
                                {
                                    Toast.makeText(getBaseContext(), "server sucess", Toast.LENGTH_LONG).show();
                                    loginLocalCredentialsPreferencesEditor.putBoolean(Constant.SAVELOGINCREDENTIALS,true);
                                    loginLocalCredentialsPreferencesEditor.putString(Constant.USERNAME,email);
                                    loginLocalCredentialsPreferencesEditor.putString(Constant.PASSWORD,password);
                                    // start a background therad that fetches all the remainig data from server and update the
                                    //internal storage for user and week workout
                                    //FetchingUserDataFromServerThread.fetchDataFromServerTherad("User_Id",getApplicationContext());
                                    //FetchingWorkoutFromServer.fetchindWorkoutDataFromServer("Trainer_Id","User_Id",getApplicationContext());
                                    onLoginSuccess();
                                }
                                else
                                {
                                   Toast.makeText(getBaseContext(), "server failed", Toast.LENGTH_LONG).show();

                                    onLoginFailed();
                                }
                                //progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                }
                            }){
                                @Override

                                protected Map<String,String> getParams()
                                {

                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("email", email);
                                    params.put("password", password);

                                    return params;
                                }

                            };
                            requestQueue.add(stringRequest);
                            //SingletonVolley.getInstance(LoginPage.this).addTorequestQueue(stringRequest);


       /*if(email.equals("admin@gmail.com")&&password.equals("admin")) {
                loginLocalCredentialsPreferencesEditor.putBoolean(Constant.SAVELOGINCREDENTIALS, true);
                loginLocalCredentialsPreferencesEditor.putString(Constant.USERNAME, email);
                loginLocalCredentialsPreferencesEditor.putString(Constant.PASSWORD, password);

                onLoginSuccess();
            }*/

        }





    }






    @Override
    public void onBackPressed() {
        // disable going back to the LoginPage
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        progressDialog.dismiss();
        sessionManager.setLogin(true);
        _loginButton.setEnabled(true);
        Intent intent=new Intent(this,UserProfile.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
