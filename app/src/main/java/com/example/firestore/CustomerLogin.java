package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogin extends AppCompatActivity {
    public EditText name;
    public EditText pass;
    public String nam,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        name = (EditText) findViewById(R.id.login_name);
        pass = (EditText) findViewById(R.id.login_pass);
        mAuth = FirebaseAuth.getInstance();





    }

    public void SignIN(View view) {
        nam = name.getText().toString();
        password = pass.getText().toString();
        Toast.makeText(this,"name=="+ nam,Toast.LENGTH_LONG).show();
        mAuth.signInWithEmailAndPassword(nam, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getApplicationContext(),Customer.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Username /n to create new Account click Sign UP",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }

    public void SignUP(View view) {
       startActivity(new Intent(this,CustomerSignUp.class));

    }
}