package com.example.moataz.cleanram.providers;

import android.appwidget.AppWidgetProvider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.moataz.cleanram.runnables.BoosterService;
import com.example.moataz.cleanram.R;
import com.example.moataz.cleanram.activities.WidgConfig;
import com.example.moataz.cleanram.services.Ramstatus;


public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";
    BoosterService boosterService = new BoosterService();
//    LoadService loadService = new LoadService();
    int color = Ramstatus.viewColor;
   RemoteViews remoteViews = WidgConfig.remoteViews;
    @Override
    public void onReceive(final Context context, Intent intent) {




final Context incontext = context;
if(intent.getAction().equals(ACTION_CLICK)) {

    Intent my_intent = new Intent(context, Ramstatus.class);
    context.startService(my_intent);
    Log.i("Autostart", "started");
final Context inContext = context;


    new Thread() {

        public void run() {

//            context.startService(new Intent(context,LoadService.class));

            boosterService.run();


        }
    }.start();


}

    if (intent.getAction().equals(
            AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {

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

    Log.d("finally"  ,"done");

    }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {



            Intent intent = new Intent(context, MyWidgetProvider.class);

            intent.setAction(ACTION_CLICK);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btn_compliance_percentage, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.btn_progress_bar, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.btng_progress_bar, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.btny_progress_bar, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.btnr_progress_bar, pendingIntent);

            appWidgetManager.updateAppWidget(thisWidget, remoteViews);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_compliance_percentage);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btng_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btny_progress_bar);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btnr_progress_bar);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.img);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,  R.id.btn_backProgress);

        }


    }




}
