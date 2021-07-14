package com.example.eshopee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ImageButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ListView lv = findViewById(R.id.Lview);
        ArrayList<String> pro = new ArrayList<>();
        pro.add("Pen");
        pro.add("Book");
        pro.add("Umbrella");
        pro.add("Kettle");
        pro.add("Workshop uniform");
        pro.add("Book");
        pro.add("Eng Graphics materials");
        pro.add("Pen");
        pro.add("Book");
        pro.add("Umbrella");
        pro.add("Kettle");
        pro.add("Workshop uniform");
        pro.add("Book");
        pro.add("Eng Graphics materials");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pro);
        lv.setAdapter(arrayAdapter);

        /*postAd = (ImageButton) findViewById(R.id.imageButton2);
        postAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPostAd();
            }
        });*/

        profile = (ImageButton) findViewById(R.id.imageButton3);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }

    /*public void openPostAd() {
        Intent intent = new Intent(this, PostAd.class);
        startActivity(intent);
    }*/

    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}