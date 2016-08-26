package com.example.moataz.cleanram.activities;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.moataz.cleanram.MyApp;
import com.example.moataz.cleanram.R;
import com.example.moataz.cleanram.providers.MyWidgetProvider;
import com.example.moataz.cleanram.services.Ramstatus;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity  {
    InterstitialAd mInterstitialAd;
    Context context;
    AppWidgetManager appWidgetManager;
    RemoteViews remoteViews;
    TextView tv1;

    ComponentName thisWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.removeView(toolbar);
        tv1=(TextView)findViewById(R.id.textView);
        Typeface face= Typeface.createFromAsset(getAssets(), "font/JF-Flat-regular.ttf");
        tv1.setTypeface(face);

        startService(new Intent(this, Ramstatus.class));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    protected void onPause() {

//            startService(new Intent(this, Ramstatus.class));

        super.onPause();
    }
    public void  setup(){
        new Ramstatus();
        context = MyApp.getContext();
        appWidgetManager = AppWidgetManager.getInstance(context);

        thisWidget = new ComponentName(context, MyWidgetProvider.class);



    }



    @Override
    protected void onDestroy() {

        startActivity(new Intent(MainActivity.this, WidgConfig.class));


//            startService(new Intent(this, Ramstatus.class));

        super.onDestroy();
    }
    public void browser(View view){

        getIntent();
        Intent intentbrowser =new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.koktil.com/"));
        startActivity(intentbrowser);
    }

}
