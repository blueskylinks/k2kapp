package com.skyiot.k2kapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

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
}