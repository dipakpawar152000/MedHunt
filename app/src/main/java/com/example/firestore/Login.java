package com.example.firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void customer(View view) {
        startActivity(new Intent(this,CustomerLogin.class));
    }

    public void owner(View view) {
        startActivity(new Intent(this,OwnerLogin.class));
    }
}