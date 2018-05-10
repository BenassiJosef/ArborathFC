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

public class Results_Activty extends AppCompatActivity {

    String URL = "http://www.futbol24.com/team/Scotland/Arbroath-FC/results/";
    TextView dates, competition, team1, score ,team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results__activty);


       dates = (TextView) findViewById(R.id.dates);
        competition = (TextView) findViewById(R.id.competition);
        team1 = (TextView) findViewById(R.id.team1);
        score = (TextView) findViewById(R.id.score);
        team2 = (TextView) findViewById(R.id.team2);
        new JSOUP().execute();

    }


    public class JSOUP extends AsyncTask<Object, Object, Void> {


        ProgressDialog dialog;
        String Dates= "";
        String Comp= "";
        String Team1= "";
        String Score= "";
        String Team2= "";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(Results_Activty.this, android.R.style.Theme_NoTitleBar_Fullscreen);

            dialog.setMessage("Loading, PLease Wait...");

            dialog.show();


        }


        @Override
        protected Void doInBackground(Object... params) {

            try {


                Document document = Jsoup.connect(URL).get();
                Elements dates = document.select("table[class = stat]").select("tr").select("td[class = data timezone");

                for (int i = 0; i < dates.size(); i++) {


                    Dates += "\n" + "\n " + (dates.get(i).text());

                }

                Elements competition = document.select("table[class = stat]").select("tr").select("td[class = comp");

                for (int i = 0; i < competition.size(); i++) {


                    Comp += "\n" + "\n " + (competition.get(i).text());

                }
                Elements team1 = document.select("table[class = stat]").select("tr").select("td[class = team4");

                for (int i = 0; i < team1.size(); i++) {


                    Team1 += "\n" + "\n " + (team1.get(i).text());

                }
                Elements score = document.select("table[class = stat]").select("tr").select("td[class = dash");

                for (int i = 0; i < score.size(); i++) {


                    Score += "\n" + "\n " + (score.get(i).text());

                }
                Elements team2 = document.select("table[class = stat]").select("tr").select("td[class = team5");

                for (int i = 0; i < team2.size(); i++) {


                    Team2 += "\n" + "\n " + (team2.get(i).text());

                }


            } catch (Exception e) {


            }
            return null;
        }



        @Override
        protected void onPostExecute (Void s){
            super.onPostExecute(s);




            dialog.dismiss();

            dates.setText("\n"+Dates);

            competition.setText("\n"+Comp);

            team1.setText("\n"+Team1);

            score.setText("\n"+Score);

            team2.setText("\n"+Team2);



        }
    }

    @Override
    public void onBackPressed(){
        // Going back to 'Main Activity'
        Intent intent = new Intent(Results_Activty.this, Main_Activity.class);
        startActivity(intent);
        finish();
    }





}
