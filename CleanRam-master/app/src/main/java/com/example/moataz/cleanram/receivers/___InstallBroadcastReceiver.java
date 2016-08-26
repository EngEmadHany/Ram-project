//package com.example.moataz.cleanram.receivers;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//
//import com.example.moataz.cleanram.MyApp;
//import com.example.moataz.cleanram.services.Ramstatus;
//
//
//public class InstallBroadcastReceiver extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        String action = null;
//        if (intent != null) {
//            action = intent.getAction();
//        }
//
//        if (action != null && Intent.ACTION_PACKAGE_ADDED.equals(action)) {
//            String dataString = intent.getDataString();
//            if (dataString != null
//                    && dataString.equals("com.example.moataz.cleanram")) {
//
//                MyApp.getContext().startService(new Intent(MyApp.getContext(), Ramstatus.class));
//            }
//        }
//    }
//}