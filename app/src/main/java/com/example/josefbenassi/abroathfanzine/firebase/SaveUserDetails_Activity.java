package com.example.josefbenassi.abroathfanzine.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josefbenassi.abroathfanzine.R;
import com.example.josefbenassi.abroathfanzine.activites.Login_Activity;
import com.example.josefbenassi.abroathfanzine.activites.Main_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SaveUserDetails_Activity extends Activity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    private TextView userEmail;
    private EditText firstname;
    private EditText secondname, address, age;
    private Button save ;

    private boolean flagmale = false;
    private boolean flagfemale = false;

    private RadioGroup radioSubcriptionGroup;
    private RadioButton subButton, fullButton, subscriptionTypeRadio;

    //RadioButton selectedButton  = (RadioButton) findViewById( radioSubcriptionGroup.getCheckedRadioButtonId());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_user_details_);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            finish();

            startActivity(new Intent(this, Login_Activity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firstname = (EditText) findViewById(R.id.firstNameEditText);
        secondname = (EditText) findViewById(R.id.secondNameEditText);
        address = (EditText) findViewById(R.id.streetNameEditText);
        age = (EditText) findViewById(R.id.ageEditText);
        save = (Button) findViewById(R.id.saveButton);

         final RadioGroup radioSubcriptionGroup = (RadioGroup) findViewById(R.id.subscriptionTypeRadio);



        subButton = (RadioButton) findViewById(R.id.subButton);
        fullButton = (RadioButton) findViewById(R.id.fullButton);

        userEmail = (TextView) findViewById(R.id.userEmail);

        userEmail.setText("Details for \n " + user.getEmail());


        save.setOnClickListener(this);

    }

    private void saveUserInformation() {


        String fname = firstname.getText().toString().trim();

        String sname = secondname.getText().toString().trim();

        String add = address.getText().toString().trim();

        String ag = age.getText().toString().trim();

        String sub = subButton.getText().toString().trim();

        String full = fullButton.getText().toString().trim();




        SharedPreferences prefs = getSharedPreferences("preferencia", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("type", fullButton.isChecked());
        editor.commit();



        UserInformation userInformation = new UserInformation(fname, sname, add, ag);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);


        Toast.makeText(this, " Information saved...", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View v) {



        if (v == save) {



            if (firstname.getText().toString().trim().equals("")) {

                Toast.makeText(this, " PLease fill in firstname", Toast.LENGTH_LONG).show();

                firstname.setError("Field Required");
            }

            else if (secondname.getText().toString().trim().equals("")) {

                Toast.makeText(this, " PLease fill in second name", Toast.LENGTH_LONG).show();

                secondname.setError("Field Required");
            }

            else if (age.getText().toString().trim().equals("")) {

                Toast.makeText(this, " Please give your age", Toast.LENGTH_LONG).show();

                age.setError("Field Required");
            }


            else if (Integer.parseInt(age.getText().toString())< 17 ){
                Toast.makeText(this, " have to be older than 16", Toast.LENGTH_LONG).show();

                age.setError("Field Required");
            }


         /*   else if( selectedButton.isChecked() == false) {

                Toast.makeText(this, " please select subscription", Toast.LENGTH_LONG).show();

              *//*  if (!flagmale) {
                    subButton.setChecked(true);
                    fullButton.setChecked(false);
                    flagmale = true;
                    flagfemale = false;
                } else {
                    flagmale = false;
                    subButton.setChecked(false);
                    fullButton.setChecked(false);
                }*//*

            }*/

            else {

                saveUserInformation();

                Intent intent = new Intent(SaveUserDetails_Activity.this, Main_Activity.class);
                startActivity(intent);
                finish();
            }


        }
    }
}