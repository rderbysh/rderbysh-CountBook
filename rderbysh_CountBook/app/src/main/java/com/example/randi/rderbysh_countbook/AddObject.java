package com.example.randi.rderbysh_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import java.util.Date;


public class AddObject extends AppCompatActivity {

    private ArrayList<ObjectCounter> objects = new ArrayList<ObjectCounter>();
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_object);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void add_toList(View view) {
        boolean valid = true;
        loadFromFile();

        EditText addObject = (EditText) findViewById(R.id.editText);
        EditText addNum = (EditText) findViewById(R.id.editText2);
        EditText addComment = (EditText) findViewById(R.id.editText3);

        //System.out.println("Object: " + addObject.getText().toString());
        //System.out.println("Count: " + addNum.getText().toString());
        //System.out.println("Comment: " + addComment.getText().toString());

        if (addObject.length() > 150 ) {
            valid = false;
            addObject.setError("Object name too long. Please make a shorter name.");
        }
        if (addObject.getText().toString().equals("")) {
            valid = false;
            addObject.setError("Field empty. Please enter required field.");
        }
        if (addNum.getText().toString().equals("")) {
            valid = false;
            addNum.setError("Please enter a count.");
        }
        if (valid) {
            ObjectCounter object = new ObjectCounter(addObject.getText().toString(), Integer.parseInt(addNum.getText().toString()), addComment.getText().toString());
            objects.add(object);
            saveInFile();
            finish();
        }
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
