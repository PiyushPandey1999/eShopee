package com.example.eshopee;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {
    EditText edOldPass, edNewPass, edConfirm;
    String username, oldPassword, newPassword, confirm;
    Button btnChange;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#BB86FC"));
        actionBar.setBackgroundDrawable(colorDrawable);

        edOldPass = (EditText) findViewById(R.id.password2);
        edNewPass = (EditText) findViewById(R.id.password3);
        edConfirm = (EditText) findViewById(R.id.password4);
        btnChange = (Button) findViewById(R.id.button3);
        myDB = new DBHelper(this);

        String[] temp = User.getInstance().getDetails();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = temp[0];

                oldPassword = edOldPass.getText().toString();
                newPassword = edNewPass.getText().toString();
                confirm = edConfirm.getText().toString();

                if(newPassword.equals(confirm)) {
                    boolean checkPasswordupdate = myDB.updatePassword(username, newPassword);

                    if(checkPasswordupdate == true) {
                        Toast.makeText(ChangePassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        openProfile();
                    }
                    else {
                        Toast.makeText(ChangePassword.this, "Password not updated", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ChangePassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}