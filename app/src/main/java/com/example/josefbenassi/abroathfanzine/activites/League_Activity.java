package com.example.josefbenassi.abroathfanzine.activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.solver.Goal;
import android.widget.TextView;

import com.example.josefbenassi.abroathfanzine.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class League_Activity extends Activity {

    String URL = "http://www.futbol24.com/national/Scotland/League-Two/2016-2017/";
    TextView positions , team, played, goalDifference, points;
    public ArrayList list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_);

        positions= (TextView) findViewById(R.id.positions);
        team = (TextView) findViewById(R.id.team);
        played = (TextView) findViewById(R.id.played);
        goalDifference = (TextView) findViewById(R.id.goalDifference);
        points =(TextView) findViewById(R.id.points);

        new JSOUP().execute();

    }

    public class JSOUP extends AsyncTask<Object, Object, String> {

            ProgressDialog dialog;
            String Position = "";
            String Team = "";
            String Played ="";
            String GoalDifference ="";
            String Points ="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        dialog =  new ProgressDialog(League_Activity.this,android.R.style.Theme_NoTitleBar_Fullscreen);

        dialog.setMessage("Loading, PLease Wait...");

            dialog.show();


        }

        @Override
        protected String doInBackground(Object... params) {

            try{



                Document document = Jsoup.connect(URL).get();
                Elements positions= document.select("table[class = stat]").select("tr").select("td[class = no");

                for(int i=0;i<positions.size();i++)
                {


                   Position+= "\n"+"\n "+(positions.get(i).text());

                }



                Elements team = document.select("table[class = stat]").select("tr").select("td[class = team");

                for(int i=0;i<team.size();i++)
                {


                    Team+= "\n"+"\n "+(team.get(i).text());

                }


                Elements played = document.select("table[class = stat]").select("tr").select("td[class = gp");

                for(int i=0;i<played.size();i++)
                {


                    Played+= "\n"+"\n "+(played.get(i).text());

                }

                Elements goalDifference = document.select("table[class = stat]").select("tr").select("td[class = plusminus");

                for(int i=0;i<goalDifference.size();i++)
                {


                    GoalDifference+= "\n"+"\n "+(goalDifference.get(i).text());

                }


                Elements points = document.select("table[class = stat]").select("tr").select("td[class = pts");

                for(int i=0;i<points.size();i++)
                {


                    Points+= "\n"+"\n "+(points.get(i).text());

                }


            } catch (Exception e){



            }


            return null;

        }



        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);


       dialog.dismiss();

            positions.setText("\n"+Position);
            team.setText("\n" + Team);
            played.setText("\n" + Played);
            goalDifference.setText("\n" + GoalDifference);
            points.setText("\n" + Points);


        }


    }


    @Override
    public void onBackPressed(){
        // Going back to 'Main Activity'
        Intent intent = new Intent(League_Activity.this, Main_Activity.class);
        startActivity(intent);
        finish();
    }

}


               /* Document document1 = Jsoup.connect(URL).get();
                Element table1 = document.select("table[class = stat]").select("tr").select("td[class = team").get(0);
                TeamNU += "\n" + table1.text();*/
               /* Document document = Jsoup.connect(URL).get();
                Element table = document.select("table[class = stat]").get(0); //select the first table.
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.

                    Team += "\n" + rows.get(i).text();

                }*/
             /*   Document document = Jsoup.connect(URL).get();
                Elements table = document.select("table[class = stat]"); //select the first table.
                Elements rows = table.select("tr");
                Elements tds = rows.select("td");



                for (int j = 1; j < rows.size(); j++) {

                    Team += "\n" + rows.get(j).text();

                }
                */




              /*  Document document = Jsoup.connect(URL).get();
                Elements elements1 = document.select("tr[class = trh team1065]" );//

                for (int j=0;j<elements1.size(); j++){

                    Team +="\n" + elements1.get(j).text();

                }

                Document document2 = Jsoup.connect(URL).get();
                Elements elements2 = document2.select("tr[class = trh team1071 col]" );//

                for (int j=0;j<elements2.size(); j++){

                    Team2 +="\n" + elements2.get(j).text();

                }*/
