package com.example.eshopee;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_up extends AppCompatActivity {
    int passwordsMatch, allFieldsFilled, enteredDetails;
    String userNameHolder, fullNameHolder, numberHolder, passwordHolder1, passwordHolder2, addressHolder;
    EditText edfn, edun, edpw1, edpw2, edpn, edadd;
    Button register;
    Boolean checkData;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
 
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

        edpw1 = (EditText) findViewById(R.id.pw1);
        edpw2 = (EditText) findViewById(R.id.pw2);
        edfn = (EditText) findViewById(R.id.fn);
        edun = (EditText) findViewById(R.id.un);
        edpn = (EditText) findViewById(R.id.pn);
        edadd = (EditText) findViewById(R.id.addr);
        register = (Button) findViewById(R.id.register);
        myDB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromEditText();
                checkEnteredDetails();

                if(enteredDetails == 1 && newUser() == true) {
                    checkData = myDB.insertUserData(fullNameHolder, userNameHolder, passwordHolder1, numberHolder, addressHolder);
                    if(checkData == true) {
                        Toast.makeText(getApplicationContext(),"Sign up successful!", Toast.LENGTH_SHORT).show();
                        openLoginActivity();
                    }
                }
                clearEditText();
            }
        });
    }

    public void getTextFromEditText() {
        fullNameHolder = edfn.getText().toString();
        userNameHolder = edun.getText().toString();
        passwordHolder1 = edpw1.getText().toString();
        passwordHolder2 = edpw2.getText().toString();
        numberHolder = edpn.getText().toString();
        addressHolder = edadd.getText().toString();
    }

    public void checkEnteredDetails() {
        enteredDetails = 0;
        passwordsMatch = 0;
        allFieldsFilled = 1;
        if(fullNameHolder.equals("") || userNameHolder.equals(""))
            allFieldsFilled = 0;
        if(numberHolder.equals("") || addressHolder.equals(""))
            allFieldsFilled = 0;

        if (passwordHolder1.equals(passwordHolder2))
            passwordsMatch = 1;

        if ( passwordsMatch == 0 && allFieldsFilled == 1 ) {
            Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
        }
        else if( passwordsMatch == 0 && allFieldsFilled == 0 ) {
            Toast.makeText(getApplicationContext(),"Passwords do not match \n Please fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else if(passwordsMatch == 1 && allFieldsFilled == 1) {
            enteredDetails = 1;
        }
        else
            Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, Login_Page.class);
        startActivity(intent);
    }

    public void clearEditText() {
        edfn.getText().clear();
        edun.getText().clear();
        edpw1.getText().clear();
        edpw2.getText().clear();
        edpn.getText().clear();
        edadd.getText().clear();
    }

    public boolean newUser() {
        Cursor result = myDB.getData(numberHolder);
        if(result.moveToFirst()) {
            Toast.makeText(getApplicationContext(),"Phone number already registered!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}