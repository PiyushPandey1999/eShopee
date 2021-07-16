package com.example.eshopee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView tvname, tvusername, tvpassword, tvphone, tvaddress;
    DBHelper myDB;
    String userHolder, passwordHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        myDB = new DBHelper(this);
        //get all details of the corresponding account
        String[] details = User.getInstance().getDetails();
        userHolder = details[0];
        passwordHolder = details[1];
        String[] userDetails = myDB.getUserDetails(userHolder, passwordHolder);

        //set textViews
        tvname = (TextView) findViewById(R.id.TextViewTextPersonName);
        tvusername = (TextView) findViewById(R.id.TextViewTextPersonName5);
        tvpassword = (TextView) findViewById(R.id.TextViewTextPersonName6);
        tvphone = (TextView) findViewById(R.id.TextViewTextPersonName7);
        tvaddress = (TextView) findViewById(R.id.TextViewTextPersonName8);

        tvname.setText(userDetails[0]);
        tvusername.setText(userDetails[1]);
        tvpassword.setText(userDetails[2]);
        tvphone.setText(userDetails[3]);
        tvaddress.setText(userDetails[4]);

    }

    public void openFirstActivity(View view) {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void openChangePassword(View view) {
        Intent intent = new Intent(this, ResetActivity.class);
        startActivity(intent);
    }
}