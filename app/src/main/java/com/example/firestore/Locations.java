package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Locations extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<LocationList> locationlist;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    private TextView latu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        progressBar = findViewById(R.id.Lprogressbar);
        recyclerView = findViewById(R.id.recycleview);
        latu = findViewById(R.id.latu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationlist = new ArrayList<>();
        adapter = new MyAdapter( locationlist,this);
        recyclerView.setAdapter(adapter);



        db = FirebaseFirestore.getInstance();

        db.collection("users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if (!queryDocumentSnapshots.isEmpty()){
                            java.util.List<DocumentSnapshot> ll = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : ll){
                                LocationList L = (LocationList) d.toObject(LocationList.class);
                                L.setId(d.getId());
                                locationlist.add(L);



                            }
                            adapter.notifyDataSetChanged();


                        }

                    }
                });


    }

    public void abc(View view) {
        startActivity(new Intent(this,Customer.class));
    }
}