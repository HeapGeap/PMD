package com.example.laba1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btn = (Button) findViewById(R.id.Track);



        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("https://r.mradx.net/img/FF/36D9E9.mp3");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });



    }

       /* try {
            CrazyMusic();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/


 /*   public void CrazyMusic() throws IOException {
        MediaPlayer music= new MediaPlayer();
        music = MediaPlayer.create(this, R.raw.frog);

        music.prepare();
        music.start();
    }
*/

    /*public void CrazyMusic2() throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(DATA_HTTP);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mediaPlayer.prepareAsync();
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Меню");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}