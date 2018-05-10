package com.example.josefbenassi.abroathfanzine.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.josefbenassi.abroathfanzine.R;

public class News_Details_Activity extends AppCompatActivity {



    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__details_);
    webview = (WebView) findViewById(R.id.webview);

        Bundle bundle = getIntent().getExtras();
        webview.loadUrl(bundle.getString("link"));

    }

}
