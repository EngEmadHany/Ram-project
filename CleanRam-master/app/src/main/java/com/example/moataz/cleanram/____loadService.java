//package com.example.moataz.cleanram;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.IBinder;
//
//import android.support.annotation.Nullable;
//
//
//public class loadService extends Service {
//    Handler m_handler;
//    Runnable m_handlerTask ;
//    Intent adintent = new Intent(MyApp.getContext(), adPop.class);
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        m_handler = new Handler();
//        m_handlerTask = new Runnable() {
//            @Override
//            public void run() {
//
//                m_handler.postDelayed(m_handlerTask, 3000);  // 10 seconds delay 10000
//        adintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        MyApp.getContext().startActivity(adintent);
//            }
//        };
//        m_handlerTask.run();
//        return START_STICKY;
//    }
//}
