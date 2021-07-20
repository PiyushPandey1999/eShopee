package com.example.eshopee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    ImageButton postAd, profile;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        ListView lv = findViewById(R.id.Lview);
        ArrayList<String> display = new ArrayList<>();
        AdDBHelper db = new AdDBHelper(this);
        List<String> result = db.getAllAds();
        for(String s: result) {
            display.add(s);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, display);
        lv.setAdapter(arrayAdapter);

        postAd = (ImageButton) findViewById(R.id.imageButton2);
        postAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPostAd();
            }
        });

        profile = (ImageButton) findViewById(R.id.imageButton3);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
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

    public void openPostAd() {
        Intent intent = new Intent(this, PostAd.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openFirstActivity(View view) {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }
}