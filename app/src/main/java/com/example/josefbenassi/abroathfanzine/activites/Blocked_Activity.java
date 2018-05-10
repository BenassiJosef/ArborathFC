package com.example.josefbenassi.abroathfanzine.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.josefbenassi.abroathfanzine.R;
import com.example.josefbenassi.abroathfanzine.firebase.SaveUserDetails_Activity;

public class Blocked_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_);



    blocked();


    }




    private void blocked() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(R.drawable.logoarborath);
        alertDialogBuilder.setTitle("Denied Access!!");
        alertDialogBuilder.setMessage("You cannot acess chat because you are partial user! Press yes to become full user");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface exitDialog, int which) {


                        exitDialog.dismiss();

                        //Going back to 'Login Screen Activity'
                        Intent intent = new Intent(Blocked_Activity.this, SaveUserDetails_Activity.class);
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

    @Override
    public void onBackPressed(){
        // Going back to 'Main Activity'
        Intent intent = new Intent(Blocked_Activity.this, Main_Activity.class);
        startActivity(intent);
        finish();
    }

}


