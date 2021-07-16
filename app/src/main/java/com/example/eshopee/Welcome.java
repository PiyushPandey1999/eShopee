package com.example.eshopee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //video background
        VideoView loginVideo = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        loginVideo.setVideoURI(uri);
        loginVideo.start();
        loginVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public void openSignUp(View view) {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, Login_Page.class);
        startActivity(intent);
    }

    //press back twice to exit
    @Override
    public void onBackPressed() {
        if(pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Press back again to EXIT", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}