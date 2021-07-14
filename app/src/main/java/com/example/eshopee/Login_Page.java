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

public class Login_Page extends AppCompatActivity {
    private VideoView loginVideo;
    EditText edUser, edPassword;
    String userHolder, passwordHolder;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login__page);

        /*changing the back button to go first activity
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                openFirstActivity();
            }
        };*/

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

        edUser = (EditText) findViewById(R.id.userName);
        edPassword = (EditText) findViewById(R.id.password);
        myDB = new DBHelper(this);

        final Button login_button = (Button) findViewById(R.id.login2);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHolder = edUser.getText().toString();
                passwordHolder = edPassword.getText().toString();
                if(myDB.checkLoginDetails(userHolder, passwordHolder) == true)
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "The username and/or password you specified are incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        final Button forgotPassword = (Button) findViewById(R.id.forgot);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordReset();
            }
        });
    }

    public void openFirstActivity() {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void passwordReset() {
        Intent intent = new Intent(this, PasswordReset.class);
        startActivity(intent);
    }
}
