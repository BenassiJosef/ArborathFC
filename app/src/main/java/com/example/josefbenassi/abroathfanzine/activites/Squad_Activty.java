package com.example.josefbenassi.abroathfanzine.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.josefbenassi.abroathfanzine.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Squad_Activty extends AppCompatActivity {


    String URL = "https://www.arbroathfc.co.uk/about/players/";
    TextView players;
    TextView playersprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad__activty);


        players = (TextView) findViewById(R.id.players);
       // playersprofile = (TextView) findViewById(R.id.playersprofile);

        new JSOUP().execute();

    }

    /**
     * this is a java doc comment
     */
    public class JSOUP extends AsyncTask<Object, Object, Void> {


        ProgressDialog dialog;
        String Players = "";
        String PlayersProfile = "";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(Squad_Activty.this, android.R.style.Theme_NoTitleBar_Fullscreen);

            dialog.setMessage("Loading, PLease Wait...");

            dialog.show();


        }


        @Override
        protected Void doInBackground(Object... params) {

            try {


                Document document = Jsoup.connect(URL).get();
                Elements players = document.select("div.menu-players-container").select("ul").select("li");

                for (int i = 0; i < players.size(); i++) {


                    Players += "\n" + "\n " + (players.get(i).text());

                }



            } catch (Exception e) {


            }
            return null;
        }



        @Override
            protected void onPostExecute (Void s){
                super.onPostExecute(s);




                dialog.dismiss();

               players.setText("\n"+Players);
           // playersprofile.setText("\n"+PlayersProfile);


            }
        }
    @Override
    public void onBackPressed(){
        // Going back to 'Main Activity'
        Intent intent = new Intent(Squad_Activty.this, Main_Activity.class);
        startActivity(intent);
        finish();
    }


    }

