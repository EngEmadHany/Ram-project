package com.example.moataz.cleanram.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moataz.cleanram.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ram.speed.booster.RAMBooster;

import java.util.Random;

public class adPop extends AppCompatActivity {

    RemoteViews remoteViews;
    private AdView mAdView;
    private InterstitialAd interstitial;
    Spanned result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_layout);
        setupWindowAnimations();




        TextView work = (TextView) findViewById(R.id.textView3);


        String first = "تم";
        String next = "<font color='#008000'> تنظيف </font>";
        String last = "الجهاز بنجاح";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(first + next + last,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(first + next + last);
        }


        work.setText(result);

//        interstitial = new InterstitialAd(adPop.this);

//        interstitial.setAdUnitId("ca-app-pub-1927620401517294/1457978962");




        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
               // .addTestDevice("8251CEFC3D9A6CFFA519DCA046B20C06")

                .build();
        mAdView.loadAd(adRequest);


        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        };


        getWindow().getDecorView().getRootView().setOnClickListener(l);
        findViewById(R.id.layout).setOnClickListener(l);
        findViewById(R.id.imageView).setOnClickListener(l);

    }

    private void setupWindowAnimations() {

        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
//        Toast.makeText(this, "اضغت زرار الرجوع لللأنهاء",
//                Toast.LENGTH_LONG).show();
    }
    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out);



    }
    private Boolean exit = true;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {


            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }}, 3 * 1000);


        }

    }


}

