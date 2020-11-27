package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Medicines extends AppCompatActivity {
    public ArrayList<String> listmed;
    public ArrayList<String> listmed2;
    ListView addmedlist;
    EditText medname;
    String mednam;
    public ArrayAdapter<String> adaptermed2;
    public ListView medlist;
    public ArrayAdapter<String> adaptermed;
    EditText verifyname;
    EditText verifyphone;
    String getverifyname,getverifyphone,verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        addmedlist = findViewById(R.id.addmedlist);
        medname = findViewById(R.id.medname);
        medlist = findViewById(R.id.medlist);
        verifyname = findViewById(R.id.verifyname);
        verifyphone = findViewById(R.id.verifyphone);


        listmed = new ArrayList<String>();
        listmed.add("cipla");
        listmed.add("paracetomal");
        listmed.add("cleansol");
        listmed.add("gloves");
        listmed.add("dettol");
        listmed.add("odomos");
        listmed.add("injection");
        listmed.add("iv");
        listmed.add("corona");
        listmed.add("medplus");
        listmed2 = new ArrayList<String>();

        adaptermed = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listmed);
        addmedlist.setAdapter(adaptermed);

        mednam = medname.getText().toString();
        medname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptermed.getFilter().filter(s);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addmedlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                medname.setText(parent.getItemAtPosition(position).toString());
            }
        });
    }
    public void addmed(View view) {


        mednam = medname.getText().toString();
        listmed2.add(mednam);
        adaptermed2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listmed2);
        medlist.setAdapter(adaptermed2);
        medname.setText("");





    }

    public void database(View view) {
        getverifyname = verifyname.getText().toString();
        getverifyphone = verifyphone.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document :task.getResult()){
                                verify = document.get("Phone").toString();
                                Toast.makeText(getApplicationContext(),"done"+getverifyphone,Toast.LENGTH_SHORT).show();


                                if (getverifyphone.equals(verify))
                                {
                                    if (!document.get("medicine").equals(null)) {
                                        ArrayList<String> dummy = (ArrayList<String>) document.get("medicine");
                                        for (String i : dummy) {
                                            listmed2.add(i);
                                        }
                                    }

                                    Map<String, Object> data = new HashMap<>();
                                    data.put("medicine", listmed2);
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("users").document(document.getId())
                                            .set(data, SetOptions.merge());
                                    Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();


                                }



                            }







                        }else {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();


                        }
                    }
                });
    }

    public void signout(View view) {
        startActivity(new Intent(this,Login.class));
    }
}