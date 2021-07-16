package com.example.eshopee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ResetActivity extends AppCompatActivity {

    private VideoView loginVideo;
    TextView username;
    EditText edpassword, rePassword;
    Button btnconfirm;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

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

         username = (TextView) findViewById(R.id.textView);
         edpassword = (EditText) findViewById(R.id.password);
         rePassword = (EditText) findViewById(R.id.retypePassword);
         btnconfirm = (Button) findViewById(R.id.confirm);
         myDB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

         btnconfirm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String user = username.getText().toString();
                 String password = edpassword.getText().toString();
                 String repassword = rePassword.getText().toString();
                 if(password.equals(repassword)) {
                     Boolean checkPasswordupdate = myDB.updatePassword(user, password);

                     if(checkPasswordupdate == true) {
                         Intent intent = new Intent(getApplicationContext(), Login_Page.class);
                         startActivity(intent);
                         Toast.makeText(ResetActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(ResetActivity.this, "Password not updated", Toast.LENGTH_SHORT).show();
                     }
                 }
                 else {
                     Toast.makeText(ResetActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
}