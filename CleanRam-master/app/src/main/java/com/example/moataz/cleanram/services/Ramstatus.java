package com.example.moataz.cleanram.services;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import com.example.moataz.cleanram.MyApp;
import com.example.moataz.cleanram.providers.MyWidgetProvider;
import com.example.moataz.cleanram.R;
import com.example.moataz.cleanram.activities.WidgConfig;
import com.example.moataz.cleanram.values;
import com.ram.speed.booster.RAMBooster;
import com.ram.speed.booster.interfaces.ScanListener;
import com.ram.speed.booster.utils.ProcessInfo;

import java.util.List;


public class Ramstatus extends Service {
    Handler m_handler;
    Runnable m_handlerTask ;
    Context context;
    AppWidgetManager appWidgetManager;
    RemoteViews remoteViews;

    ComponentName thisWidget;
    static boolean ran = false;
    RAMBooster booster;
    values val = new values();
    static Integer percentage;
    public static int viewColor;

    public void  setup(){

    context = MyApp.getContext();
    booster = new RAMBooster(context);
    appWidgetManager = AppWidgetManager.getInstance(context);

    thisWidget = new ComponentName(context, MyWidgetProvider.class);



}
        public void getRam(){

            setup();

       booster.setScanListener(new ScanListener() {
                @Override
                public void onStarted() {}
                @Override
                public void onFinished(long availableRam, long totalRam, List<ProcessInfo > appsToClean) {

                    val.setAvilable(availableRam);
                    val.setTotal(totalRam);
                }
            });
            booster.startScan(true);

            percentage  =  (int)(val.getLAvilable() * 100.0 / val.getLTotal() + 0.5);
            val.setSaved(percentage);
            if(val.getLTotal() > 0){
            percentage = 100-percentage;} else {percentage = val.getSaved();}


            if(WidgConfig.remoteViews != null){
                remoteViews = WidgConfig.remoteViews;
                if (percentage > 75){

                    remoteViews.setTextViewText(R.id.btn_compliance_percentage,percentage.toString()+"%");
                    remoteViews.setTextColor(R.id.btn_compliance_percentage,getResources().getColor(R.color.end));
                    remoteViews.setProgressBar(R.id.btnr_progress_bar,100,percentage,false);
                    remoteViews.setViewVisibility(R.id.btng_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btny_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.img,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_backProgress,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_compliance_percentage,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btnr_progress_bar,View.VISIBLE);
                }

                else if (percentage > 55){


                    remoteViews.setTextViewText(R.id.btn_compliance_percentage,percentage.toString()+"%");
                    remoteViews.setTextColor(R.id.btn_compliance_percentage,getResources().getColor(R.color.center));
                    remoteViews.setProgressBar(R.id.btny_progress_bar,100,percentage,false);
                    remoteViews.setViewVisibility(R.id.btng_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btnr_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.img,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_backProgress,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_compliance_percentage,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btny_progress_bar,View.VISIBLE);

                } else {


                    remoteViews.setTextViewText(R.id.btn_compliance_percentage,percentage.toString()+"%");
                    remoteViews.setTextColor(R.id.btn_compliance_percentage,getResources().getColor(R.color.start));
                    remoteViews.setProgressBar(R.id.btng_progress_bar,100,percentage,false);
                    remoteViews.setViewVisibility(R.id.btny_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btnr_progress_bar,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.img,View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_backProgress,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btn_compliance_percentage,View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.btng_progress_bar,View.VISIBLE);

                }


                appWidgetManager.updateAppWidget(thisWidget, remoteViews);
            }



    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        m_handler = new Handler();
        m_handlerTask = new Runnable() {
            @Override
            public void run() {

                getRam();
                m_handler.postDelayed(m_handlerTask, 6000);  // 10 seconds delay 10000
            }
        };
        m_handlerTask.run();
        return START_STICKY;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }




    @Override public void onTaskRemoved(Intent rootIntent){
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1,
                restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }
}