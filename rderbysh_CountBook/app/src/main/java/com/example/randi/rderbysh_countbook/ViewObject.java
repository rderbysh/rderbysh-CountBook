package com.example.randi.rderbysh_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewObject extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ArrayList<ObjectCounter> objects;
    private Integer arrayPosition;
    private ObjectCounter list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_object);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        arrayPosition = getIntent().getIntExtra("position", 0);
        System.out.println("position: " + arrayPosition);
        list = objects.get(arrayPosition);
        System.out.println("list: " + list);

        fillInfo(arrayPosition, list);

    }

    private void fillInfo(int position, ObjectCounter list) {
        String name = list.getObject();
        //String date = list.getDate();
        Integer init = list.getInitialCounter();
        Integer currentCounter = list.getCurrentCounter();
        System.out.println("name string: " + name);

        setContentView(R.layout.activity_view_object);
        TextView object1 = (TextView) findViewById(R.id.textView);
        object1.setText(name);

        TextView init1 = (TextView) findViewById(R.id.textView7);
        init1.setText(init.toString());

        TextView date1 = (TextView) findViewById(R.id.DateCreated);
        //date1.setText(date);
        //System.out.println("date: " + date);
        //date1.setText();
        TextView counter_curr = (TextView) findViewById(R.id.textNum);
        if (currentCounter != null) {
            counter_curr.setText(currentCounter.toString());
        } else {
            counter_curr.setText(init.toString());
            list.setCurrent_counter(init);
            System.out.println("new list: " + list);

        }

    }

    public void increment(View view) {
        Integer current = list.getCurrentCounter();
        current += 1;
        TextView counter_curr = (TextView) findViewById(R.id.textNum);
        counter_curr.setText(current.toString());
        list.setCurrent_counter(current);
        saveInFile();
    }

    public void decrement(View view) {
        Integer current = list.getCurrentCounter();
        current -= 1;
        TextView counter_curr = (TextView) findViewById(R.id.textNum);
        counter_curr.setText(current.toString());
        list.setCurrent_counter(current);
        saveInFile();
    }

    public void edit_Button(View view) {
        Intent intent1 = new Intent(ViewObject.this, EditObject.class);
        startActivity(intent1);
    }

    public void delete(View view) {
        objects.remove(arrayPosition);
        saveInFile();
        finish();
    }

    private void loadFromFile() {
        try{
            FileInputStream file_in = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(file_in));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<ObjectCounter>>() {}.getType();
            objects = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream file_out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(file_out);
            Gson gson = new Gson();
            gson.toJson(objects, writer);
            writer.flush();
            file_out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
