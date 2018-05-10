package com.example.josefbenassi.abroathfanzine.activites;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.josefbenassi.abroathfanzine.R;
import com.example.josefbenassi.abroathfanzine.rss.ReadRss;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class Main_Activity extends Activity implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    RecyclerView recyclerView;



    private TextView emailNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        auth = FirebaseAuth.getInstance();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        emailNavigation=(TextView)header. findViewById(R.id.emailNavigation);

        FirebaseUser user = auth.getCurrentUser();

        String user_name = user.getEmail();

        emailNavigation.setText(user_name);


        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);

        ReadRss readRss = new ReadRss(this,recyclerView);
        readRss.execute();


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
        getMenuInflater().inflate(R.menu.main_, menu);




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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean valor;
        SharedPreferences prefs = getSharedPreferences("preferencia", Context.MODE_PRIVATE);

       valor = prefs.getBoolean("type", false);

        if (id == R.id.nav_feed) {
            // Handle the camera action
        } else if (id == R.id.nav_squad) {
            Intent intent = new Intent(Main_Activity.this, Squad_Activty.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_league) {
            Intent intent = new Intent(Main_Activity.this, League_Activity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_chat) {


            if(valor == true) {

                Intent intent = new Intent(Main_Activity.this, Chat_Activity.class);
                startActivity(intent);
                finish();
            } else {


                Intent intent = new Intent(Main_Activity.this, Blocked_Activity.class);
                startActivity(intent);
                finish();

            }

        } else if (id == R.id.nav_results) {

            Intent intent = new Intent(Main_Activity.this, Results_Activty.class);
            startActivity(intent);
            finish();


        } else if (id == R.id.nav_logout) {

           logout();

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //sign out method
    //public void signOut() {auth.signOut();}

    //Logout function
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(R.drawable.logoarborath);
        alertDialogBuilder.setTitle("Logout Arbroath FC");
        alertDialogBuilder.setMessage("Are you sure you want to logout?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface exitDialog, int which) {

                        auth.signOut();

                        exitDialog.dismiss();

                        //Going back to 'Login Screen Activity'
                        Intent intent = new Intent(Main_Activity.this, Login_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface exitDialog, int which) {
                        exitDialog.dismiss();
                    }
                });

        //Showing the alert dialog
        AlertDialog exitAlertDialog = alertDialogBuilder.create();
        exitAlertDialog.show();

    }

}
