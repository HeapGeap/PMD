package com.example.laba1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.laba1.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySecondBinding binding;

    private ProgressBar nPBar;
    private ProgressBar nPBar2;

    private ProgressBar nPBar3;
    private double Progress_Status = 0;
    private double Progress_Status2 =0;
    private double Progress_Status3 =0;

    public static final String txtData = "123445";

    private Handler nHandler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        MyDatabase mydb = new MyDatabase(this);

        SQLiteDatabase sqdb = mydb.getWritableDatabase();


        String insertQuery = "INSERT INTO " +
                MyDatabase.TABLE_NAME +" (" + MyDatabase.UNAME + ") VALUES ('" +
                txtData.toString() + "')";
        sqdb.execSQL(insertQuery);


        Cursor cursor = sqdb.query(MyDatabase.TABLE_NAME,new String[]{
                        MyDatabase.UID,MyDatabase.UNAME},
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.UID));
            String name = cursor.getString(cursor.getColumnIndex(MyDatabase.UNAME));
            Log.i("LOG_TAG", "ROW" + id + "HAS UNAME" + name);
        }
        cursor.close();

        sqdb.close();
        mydb.close();



        nPBar = (ProgressBar) findViewById(R.id.progressBar1);
        nPBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        nPBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (Progress_Status < 100){
                    Progress_Status+=0.5;
                    android.os.SystemClock.sleep(50);
                    nHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            nPBar.setProgress((int)Progress_Status);
                        }
                    });
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (Progress_Status2 < 100){
                    Progress_Status2+=0.25;
                    android.os.SystemClock.sleep(50);
                    nHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            nPBar2.setProgress((int)Progress_Status2);
                        }
                    });
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (Progress_Status3 < 100){
                    Progress_Status3+=0.1;
                    android.os.SystemClock.sleep(50);
                    nHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            nPBar3.setProgress((int)Progress_Status3);
                        }
                    });
                }

            }
        }).start();
        AlertDia();
    }

    public void AlertDia(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Видим диалоговое окно?")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.setTitle("Поговорим?");
        alert.show();
    }



    }
