package com.example.moataz.cleanram.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.moataz.cleanram.MyApp;
import com.example.moataz.cleanram.services.Ramstatus;


public class autostart extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent)
    {

        String action = null;
        if (intent != null) {
            action = intent.getAction();
        }

        if (action != null && Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            String dataString = intent.getDataString();
            if (dataString != null
                    && dataString.equals("com.example.moataz.cleanram")) {

                MyApp.getContext().startService(new Intent(MyApp.getContext(), Ramstatus.class));
            }
        }else  if (action != null && Intent.ACTION_BOOT_COMPLETED.equals(action))
        {

            Intent my_intent = new Intent(context, Ramstatus.class);
            context.startService(my_intent);
            Log.i("Autostart", "started");
        }
    }
}