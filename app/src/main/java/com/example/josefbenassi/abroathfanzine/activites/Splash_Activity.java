package com.example.josefbenassi.abroathfanzine.activites;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.example.josefbenassi.abroathfanzine.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash_Activity extends AwesomeSplash{

    MediaPlayer mp;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        /* you don't have to override every property */

        //set music

        mp = MediaPlayer.create(this, R.raw.tuneforsplash2);

        mp.start();


        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.bg_splash); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logoarborath); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.ZoomIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Title
        configSplash.setTitleSplash("Arbroath FC...");
        configSplash.setTitleTextColor(R.color.text_splash);
        configSplash.setTitleTextSize(45f); //float value
        configSplash.setAnimTitleDuration(8000);
        configSplash.setAnimTitleTechnique(Techniques.Flash);
        configSplash.setTitleFont("font/splashtextfont.ttf");



    }

    @Override

    protected void onPause() {

        // TODO Auto-generated method stub

        super.onPause();

        finish();

        mp.release();

    }









    @Override
    public void animationsFinished() {


        startActivity(new Intent(Splash_Activity.this, Login_Activity.class));
    }



}
