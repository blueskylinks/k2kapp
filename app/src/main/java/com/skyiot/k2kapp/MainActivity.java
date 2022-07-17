package com.skyiot.k2kapp;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1=(ListView) findViewById(R.id.listview1);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("A1");
        arrayList.add("A2");
        arrayList.add("A3");

        ArrayAdapter array_d=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        l1.setAdapter(array_d);

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
}