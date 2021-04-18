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

public class OwnerSignUp extends AppCompatActivity {
    EditText ownername,ownerpass;
    String ownernam,ownerpassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_sign_up);
        ownername = findViewById(R.id.ownersignup_name);
        ownerpass =  findViewById(R.id.ownersignup_pass);
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
}