package com.example.eshopee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class PostAd extends AppCompatActivity {
    private EditText e1, e2, e3;
    AdDBHelper db;
    ImageView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_ad);
        e3 = (EditText) findViewById(R.id.editTextTextPersonName2);
        e2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        e1 = (EditText) findViewById(R.id.editTextTextPersonName4);
        c = findViewById(R.id.imageView3);

        db = new AdDBHelper(this);
    }

    public void post(View v) {
        Boolean cinsert;
        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        if(!s1.equals("") && !s2.equals("") && !s3.equals("") ) {
            cinsert = db.insert(s1, s2, s3);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            cinsert = false;
        }

        if (cinsert) {
            Toast.makeText(getApplicationContext(), "Entered in Database Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void upload(View v) {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        c.setImageURI(uri);
    }
}
