package com.example.moataz.cleanram.runnables;

import android.app.ActivityManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.example.moataz.cleanram.MyApp;
import com.example.moataz.cleanram.R;
import com.example.moataz.cleanram.activities.WidgConfig;
import com.example.moataz.cleanram.activities.adPop;
import com.example.moataz.cleanram.providers.MyWidgetProvider;
import com.example.moataz.cleanram.services.Ramstatus;
import com.example.moataz.cleanram.values;
import com.ram.speed.booster.RAMBooster;
import com.ram.speed.booster.interfaces.CleanListener;
import com.ram.speed.booster.interfaces.ScanListener;
import com.ram.speed.booster.utils.ProcessInfo;


import java.util.List;


public class BoosterService implements Runnable {
    RAMBooster booster;
    values val = new values();
    Integer availMembefore;
    Integer availMemafter;
    static Integer loss;
    ActivityManager mActivityManager = (ActivityManager) MyApp.getContext().getSystemService(Context.ACTIVITY_SERVICE);;
    Context context = MyApp.getContext();
    AppWidgetManager appWidgetManager;
    RemoteViews remoteViews;
    ComponentName thisWidget;
    Handler m_handler;
    Runnable m_handlerTask ;
    public void freeMemory(Context context){

                changeIcon();


        booster = new RAMBooster(context);
        booster.setScanListener(new ScanListener() {
            @Override
            public void onStarted() {

            }
            @Override
            public void onFinished(long availableRam, long totalRam, List<ProcessInfo> appsToClean) {

                val.setAvBefore(availableRam);
                val.setTotal(totalRam);
                Log.d("loss scan",availableRam+ "");
                booster.startClean();
                closeAllAPPS();
//                val.setAvAfter(availableRam);

            }
        });


        booster.setCleanListener(new CleanListener() {
            @Override
            public void onStarted() {


            }
            @Override
            public void onFinished(long availableRam, long totalRam) {
                val.setAvAfter(availableRam);
                Log.d("loss clean",availableRam+ "");
                val.setTotal(totalRam);


            }
        });
//        booster.setDebug(true);
        booster.startScan(true);


    }
    public void closeAllAPPS(){

        List<ApplicationInfo> packages;
        PackageManager pm;
        pm = MyApp.getContext().getPackageManager();
        //get a list of installed apps.
        packages = pm.getInstalledApplications(0);

        ActivityManager mActivityManager = (ActivityManager)MyApp.getContext().getSystemService(Context.ACTIVITY_SERVICE);

        for (ApplicationInfo packageInfo : packages) {
            if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
            if(packageInfo.packageName.equals("com.example.moataz.cleanram")) continue;
            mActivityManager.killBackgroundProcesses(packageInfo.packageName);

        }

        Intent adintent = new Intent(MyApp.getContext(), adPop.class);
        adintent.putExtra("loss", values.getLoss());
        adintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApp.getContext().startActivity(adintent);
    }


    public void  setup(){
        new Ramstatus();
        context = MyApp.getContext();
        appWidgetManager = AppWidgetManager.getInstance(context);

        thisWidget = new ComponentName(context, MyWidgetProvider.class);



    }
    public void changeIcon(){

        setup();



        if(WidgConfig.remoteViews!= null){
            remoteViews = WidgConfig.remoteViews;
            remoteViews.setViewVisibility(R.id.btn_backProgress, View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btn_compliance_percentage,View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btng_progress_bar, View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btny_progress_bar,View.INVISIBLE);
            remoteViews.setViewVisibility(R.id.btnr_progress_bar,View.INVISIBLE);

            remoteViews.setViewVisibility(R.id.img, View.VISIBLE);

            appWidgetManager.updateAppWidget(thisWidget, remoteViews);


            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MyWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_compliance_percentage);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btng_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btny_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btnr_progress_bar);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.img);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_backProgress);
        } else {

        }



    }



    @Override
    public void run() {

        freeMemory(MyApp.getContext());

        availMembefore  = val.getLAvBefore().intValue();
        availMemafter = val.getLAvAfter().intValue();


        loss = availMemafter - availMembefore;
        Log.d("loss A",availMemafter +"");
        Log.d("loss B",availMembefore +"");
        Log.d("loss",loss +"");
        if (loss <= 0){
            values.setLoss(0);
        } else {
            values.setLoss(loss);
        }

    }
}