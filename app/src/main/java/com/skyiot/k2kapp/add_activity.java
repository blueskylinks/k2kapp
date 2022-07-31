package com.skyiot.k2kapp;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class add_activity extends AppCompatActivity {
    ListView l1;
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editText=(EditText) findViewById(R.id.Birthday);
        b1=(Button) findViewById(R.id.submit);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_activity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_data();
            }
        });


    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void add_data() {
        EditText date1 = (EditText) findViewById(R.id.Birthday);
        EditText src = (EditText) findViewById(R.id.text_src);
        EditText dest = (EditText) findViewById(R.id.text_dst2);
        EditText gsupplier = (EditText) findViewById(R.id.text_suplier);
        EditText mtl = (EditText) findViewById(R.id.text_mtl);
        Map<String, Object> user = new HashMap<>();
        user.put("date", date1.getText().toString());
        user.put("source", src.getText().toString());
        user.put("destination", dest.getText().toString());
        user.put("supplier", gsupplier.getText().toString());
        user.put("material", mtl.getText().toString());


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        clear_data();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }

    public void clear_data() {
        EditText date1 = (EditText) findViewById(R.id.Birthday);
        EditText src = (EditText) findViewById(R.id.text_src);
        EditText dest = (EditText) findViewById(R.id.text_dst2);
        EditText gsupplier = (EditText) findViewById(R.id.text_suplier);
        EditText mtl = (EditText) findViewById(R.id.text_mtl);

        date1.setText("");
        src.setText("");
        dest.setText("");
        gsupplier.setText("");
        mtl.setText("");
    }





}