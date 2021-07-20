package com.example.eshopee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView tvname, tvusername, tvpassword, tvphone, tvaddress;
    DBHelper myDB;
    String userHolder, passwordHolder;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
 
        myDB = new DBHelper(this);
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
        btn = (Button) findViewById(R.id.button5);

        if(userDetails.length > 0) {
            tvname.setText(userDetails[0]);
            tvusername.setText(userDetails[1]);
            tvpassword.setText(userDetails[2]);
            tvphone.setText(userDetails[3]);
            tvaddress.setText(userDetails[4]);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        myDB = new DBHelper(this);
        String[] details = User.getInstance().getDetails();
        userHolder = details[0];
        passwordHolder = details[1];
        String[] userDetails = myDB.getUserDetails(userHolder, passwordHolder);

        if(userDetails.length > 0) {
            tvname.setText(userDetails[0]);
            tvusername.setText(userDetails[1]);
            tvpassword.setText(userDetails[2]);
            tvphone.setText(userDetails[3]);
            tvaddress.setText(userDetails[4]);
        }
        super.onStart();
    }

    public void openFirstActivity(View view) {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    public void openChangePassword(View view) {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }
}