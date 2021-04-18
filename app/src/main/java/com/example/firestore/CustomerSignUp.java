package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerSignUp extends AppCompatActivity {
    EditText name,pass;
    String nam,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        name = findViewById(R.id.signup_name);
        pass = findViewById(R.id.signup_pass);
        mAuth = FirebaseAuth.getInstance();

    }

    public void SignUP(View view) {
        nam = name.getText().toString();
        password = pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(nam, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),Customer.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Username /n to create new Account click Sign UP",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}