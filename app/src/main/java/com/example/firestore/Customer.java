package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Customer extends AppCompatActivity {
    public SearchView mySearchView;
     public ListView myList;
    public ArrayList<String> list;
    public ArrayList<QueryDocumentSnapshot> list2;
    public ArrayAdapter<String> adapter;
    public ArrayAdapter<QueryDocumentSnapshot> adapter2;

    public String str;
    public TextView doc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer);


        mySearchView = (SearchView) findViewById(R.id.mySearchView);
        myList = (ListView) findViewById(R.id.myList);
        doc = (TextView) findViewById(R.id.documents);
        list = new ArrayList<String>();



        list.add("cipla");
        list.add("paracetomal");
        list.add("cleansol");
        list.add("gloves");
        list.add("dettol");
        list.add("odomos");
        list.add("injection");
        list.add("iv");
        list.add("corona");
        list.add("medplus");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);


                return false;
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mySearchView.setQuery(list.get(position),true);
                str = list.get(position);




            }
        });









    }

    public void pqr(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("nam",str);
        startActivity(intent);


//        Intent intent = new Intent(this,MapsActivity.class);
//        intent.putExtra("abc",list2);
//        startActivity(intent);

    }


    public void signout(View view) {
        startActivity(new Intent(this,Login.class));
    }
}