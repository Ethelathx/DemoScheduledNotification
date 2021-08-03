package com.example.demoschedulednotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

    int reqCode = 12345;
    @Override
    public void onReceive(Context context, Intent intent) {
        //-----------------Setup-----------------
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        //-----------------Setup-----------------

        //==================------DefaultNotifi-----------========================
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }
        //==================------DefaultNotifi-----------========================

        //-----------------ActivityToLaunchedUponClicked--------------------
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity
                (context, reqCode, i, PendingIntent.FLAG_CANCEL_CURRENT);
        //-----------------ActivityToLaunchedUponClicked--------------------


        //---------------------------NotificationBuilding-------------------------
        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Amazing Offer!");
        builder.setContentText("Subject");
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);

        Notification n = builder.build();
        //---------------------------NotificationBuilding-------------------------
        notificationManager.notify(123, n);
    }
}