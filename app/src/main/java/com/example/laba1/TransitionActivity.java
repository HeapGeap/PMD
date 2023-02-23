package com.example.laba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ImageWork();


    }

    public void ImageWork(){
        ImageView imageView = findViewById(R.id.transition_image);
        final TransitionDrawable transitionDrawable = (TransitionDrawable) imageView.getDrawable();

        Button startTransition = findViewById(R.id.start_transition);
        Button reverseTransition = findViewById(R.id.reverse_transition);
        startTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transitionDrawable.startTransition(1000);
            }
        });

        reverseTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transitionDrawable.reverseTransition(1000);
            }
        });
    }

    public void Brows(View v){
        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://github.com/HeapGeap");
    }
}