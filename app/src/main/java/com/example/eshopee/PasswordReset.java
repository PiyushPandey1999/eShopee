package com.example.eshopee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordReset extends AppCompatActivity {

    private VideoView loginVideo;
    EditText edUsername;
    Button btnReset;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset);

        //video background
        VideoView loginVideo = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        loginVideo.setVideoURI(uri);
        loginVideo.start();
        loginVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        edUsername = (EditText) findViewById(R.id.username);
        btnReset = (Button) findViewById(R.id.reset);
        myDB = new DBHelper(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUsername.getText().toString();
                Boolean checkUser = myDB.checkUsername(user);
                if(checkUser == true) {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PasswordReset.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}