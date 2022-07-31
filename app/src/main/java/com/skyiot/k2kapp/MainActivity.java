package com.skyiot.k2kapp;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView l1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> arrayList=new ArrayList<>();
    //EditText theFilter=(EditText) findViewById(R.id.edit_search);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/*        l1=(ListView) findViewById(R.id.listview1);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("A1");
        arrayList.add("A2");
        arrayList.add("A3");

        ArrayAdapter array_d=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        l1.setAdapter(array_d);*/

        get_data();

    }

    public void add_entries(View view) {
        Intent intent = new Intent(this, add_activity.class);
        intent.putExtra(EXTRA_MESSAGE, "Sample Message");
        startActivity(intent);
    }

    public void move_home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Sample Message Move Home");
        startActivity(intent);
    }


    public void get_data(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i("AAA", document.getId() + " => " + document.getData());
                                Log.i("DDD", document.get("destination").toString());
                                String s1 = document.get("date")+"   |   "+document.get("source").toString() +"  :  "+document.get("destination").toString();
                                Log.i("CCC",s1);
                                array_add(s1);
                            }

                        } else {
                            Log.i("GGG", "Error getting documents: ", task.getException());
                        }

                    }
                });



    }

    public void array_add(String s){
        arrayList.add(s);
        l1=(ListView) findViewById(R.id.listview1);
        ArrayAdapter array_d=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        l1.setAdapter(array_d);
/*
        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MainActivity.this).array_d.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }

}