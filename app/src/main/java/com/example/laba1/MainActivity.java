package com.example.laba1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.laba1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "MyChannel";

    private static final String ACTION_SNOOZE = "Кнопка";

    private static final String EXTRA_NOTIFIC_ID = "Кнопочка";

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance  = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);

            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,"Это уведомление",Toast.LENGTH_LONG);
       toast.setGravity(Gravity.CENTER, 500,500);
       toast.show();   //first_test


        createNotificationChannel();




        //Intent notificationintent = new Intent(context, MainActivity.class); //пример 2 для уведомления в статус баре

        Intent notificationintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/freaky_timelapse"));

        Intent snoozeIntent = new Intent(this, BroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFIC_ID,0);

        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(this,0,snoozeIntent,0);


        PendingIntent contentintent = PendingIntent.getActivity(context,
                0, notificationintent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = context.getResources();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_car)
                .setColor(Color.RED)
                .setContentTitle("Посетите сайт")
                .setContentText("https://vk.com/freaky_timelapse")
                .setTicker("Info").setWhen(System.currentTimeMillis())
                .setContentIntent(contentintent) //для примера с сайтом
                .addAction(R.drawable.sharp_lock,getString(R.string.snooze),snoozePendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX);



        Notification notification = mBuilder.build();


        NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID,notification);


    }



    public void on_click(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void menu_click(View v){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void transitions_click(View v){
        Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
        startActivity(intent);
    }

    public void files_click(View v){
        Intent intent = new Intent(MainActivity.this, FilesActivity.class);
        startActivity(intent);
    }

    public void Namefiles_click(View v){
        Intent intent = new Intent(MainActivity.this, NameFilesActivity.class);
        startActivity(intent);
    }


    public void SmS_Function(View v){
        SmsManager sendSMS = SmsManager.getDefault();
        String num = "89515014411";
        String msg = "Му first SMS";
        sendSMS.sendTextMessage(num, null, msg, null, null);

        String SENT_SMS_FLAG = "SENТ_SMS";
        String DELIVER_SMS_FLAG = "DELIVER SMS";

        Intent sent_sms = new Intent(SENT_SMS_FLAG);

        PendingIntent spin = PendingIntent.getBroadcast(this,0,sent_sms,0);

        Intent deliever_sms = new Intent(DELIVER_SMS_FLAG);

        PendingIntent dpin = PendingIntent.getBroadcast(this,0,deliever_sms,0);

        BroadcastReceiver sentReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        break;
                    default:
                        break;
                }
            }
        };


        BroadcastReceiver deliverReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        break;
                    default:
                        break;
                }
            }
        };

        registerReceiver(sentReciever, new IntentFilter(SENT_SMS_FLAG));
        registerReceiver(deliverReciever, new IntentFilter(DELIVER_SMS_FLAG));



    }







}