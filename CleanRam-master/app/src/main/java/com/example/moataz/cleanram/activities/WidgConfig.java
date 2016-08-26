package com.example.moataz.cleanram.activities;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.moataz.cleanram.MyApp;
import com.example.moataz.cleanram.R;
import com.example.moataz.cleanram.services.Ramstatus;


public class WidgConfig extends AppCompatActivity {
    static public RemoteViews remoteViews  = new RemoteViews(MyApp.getContext().getPackageName(),
            R.layout.fullbtn);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setResult(RESULT_CANCELED);

        int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Log.d("CONFIG","CONFIG RUN");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MyApp.getContext());
        RemoteViews views = new RemoteViews(MyApp.getContext().getPackageName(),
                R.layout.calc);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        MyApp.getContext().startService(new Intent(MyApp.getContext(), Ramstatus.class));
        finish();
        super.onCreate(savedInstanceState);

    }
}
