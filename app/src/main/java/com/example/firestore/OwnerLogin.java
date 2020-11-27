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

public class OwnerLogin extends AppCompatActivity {
    public EditText ownername;
    public EditText ownerpass;
    public String ownernam,ownerpassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        ownername = (EditText) findViewById(R.id.ownerlogin_name);
        ownerpass = (EditText) findViewById(R.id.ownerlogin_pass);
        mAuth = FirebaseAuth.getInstance();
    }

    public void ownerSignup(View view) {
        ownernam = ownername.getText().toString();
        ownerpassword = ownerpass.getText().toString();
        mAuth.createUserWithEmailAndPassword(ownernam, ownerpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Username /n to create new Account click Sign UP",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });

    }


    public void ownerSignin(View view) {
        ownernam = ownername.getText().toString();
        ownerpassword = ownerpass.getText().toString();
        Toast.makeText(this,"name=="+ ownernam,Toast.LENGTH_LONG).show();
        mAuth.signInWithEmailAndPassword(ownernam,ownerpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Please Enter Valid Username /n to create new Account click Sign UP",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }
}