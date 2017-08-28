package com.example.sameershekhar.hebecollins.activities.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.LoginPage;
import com.example.sameershekhar.hebecollins.activities.fcm.SessionManager;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UpcommingBirthday;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserProgressFrag;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserPhotoGallaryFrag;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserProfileFragment;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserProfileHomeFrag;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserTrainerDetialsFrag;
import com.example.sameershekhar.hebecollins.activities.ui.fragments.UserWeekWorkoutsFrag;

public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,UserProgressFrag.OnFragmentInteractionListener,
        UserPhotoGallaryFrag.OnFragmentInteractionListener,UserProfileFragment.OnFragmentInteractionListener
,UserProfileHomeFrag.OnFragmentInteractionListener,UserTrainerDetialsFrag.OnFragmentInteractionListener,
        UserWeekWorkoutsFrag.OnFragmentInteractionListener,
        UpcommingBirthday.OnFragmentInteractionListener{
    FragmentManager fragmentManager;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sessionManager=new SessionManager(getApplicationContext());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();

        UserProfileHomeFrag userProfileHomeFrag =new UserProfileHomeFrag();
        fragmentManager.beginTransaction().replace(R.id.frame_container, userProfileHomeFrag).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==R.id.action_logout)
        {
            sessionManager.setLogin(false);
            Intent intent = new Intent(UserProfile.this, LoginPage.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ActionBar actionBar = getSupportActionBar();
        if(id==R.id.nav_home)
        {
            actionBar.setTitle("Home");
            UserProfileHomeFrag userProfileHomeFrag =new UserProfileHomeFrag();
            fragmentManager.beginTransaction().replace(R.id.frame_container, userProfileHomeFrag).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_profile) {


            actionBar.setTitle("Profile");
            UserProfileFragment userProfileFragment=new UserProfileFragment();
            fragmentManager.beginTransaction().replace(R.id.frame_container,userProfileFragment).addToBackStack(null).commit();

            // Handle the camera action
        }  else if (id == R.id.nav_trainer_details) {

            actionBar.setTitle("Trainer Details");
            UserTrainerDetialsFrag userTrainerDetialsFrag=new UserTrainerDetialsFrag();
            fragmentManager.beginTransaction().replace(R.id.frame_container,userTrainerDetialsFrag).addToBackStack(null).commit();

        } else if (id == R.id.nav_progress) {
            actionBar.setTitle("User progress");
            UserProgressFrag userProgressFrag=new UserProgressFrag();
            fragmentManager.beginTransaction().replace(R.id.frame_container,userProgressFrag).addToBackStack(null).commit();

        } else if (id == R.id.nav_week_planes) {

            actionBar.setTitle("Week Workout");
            UserWeekWorkoutsFrag userWeekWorkoutsFrag=new UserWeekWorkoutsFrag();
            fragmentManager.beginTransaction().replace(R.id.frame_container,userWeekWorkoutsFrag).addToBackStack(null).commit();


        }  else if (id == R.id.nav_upcomming_birthday) {

            actionBar.setTitle("Week Workout");
            UpcommingBirthday upcommingbirthday=new UpcommingBirthday();
            fragmentManager.beginTransaction().replace(R.id.frame_container,upcommingbirthday).addToBackStack(null).commit();


        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
