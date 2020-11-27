package com.example.firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Medical extends AppCompatActivity {
    TextView medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        medical = findViewById(R.id.medical);
        String title = getIntent().getStringExtra("title");
        medical.setText(title);

    }

    public void signout(View view) {
        startActivity(new Intent(this,Login.class));
    }
}