package com.example.randi.rderbysh_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

public class EditObject extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ArrayList<ObjectCounter> objects;
    private Integer arrayPosition;
    private ObjectCounter list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_object);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        EditText editinitial = (EditText) findViewById(R.id.editinit);
        EditText editcom = (EditText) findViewById(R.id.editcomment);

        arrayPosition = getIntent().getIntExtra("position", 0);

        list = objects.get(arrayPosition);

        editinitial.setText(list.getInitialCounter().toString());
        editcom.setText(list.getComment().toString());

    }

    public void save(View view) {

        EditText editinitial = (EditText) findViewById(R.id.editinit);
        EditText editcom = (EditText) findViewById(R.id.editcomment);

        list.setInitial_counter(Integer.parseInt(editinitial.getText().toString()));
        list.setCurrent_counter(Integer.parseInt(editinitial.getText().toString()));
        list.setComment(editcom.getText().toString());

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
