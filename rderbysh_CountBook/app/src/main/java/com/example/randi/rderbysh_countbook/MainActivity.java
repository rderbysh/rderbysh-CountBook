package com.example.randi.rderbysh_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private ListView oldObjectsList;
    private static final String FILENAME = "file.sav";
    private ArrayList<ObjectCounter> objects = new ArrayList<ObjectCounter>();
    private ArrayAdapter<ObjectCounter> adapter;

    public static final String EXTRA_MESSAGE = "com.example.randi.rderbysh_countbook.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        oldObjectsList = (ListView) findViewById(R.id.oldObjectsList);

        adapter = new ArrayAdapter<ObjectCounter>(this, android.R.layout.simple_list_item_1, objects);
        oldObjectsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        oldObjectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ObjectCounter entry = (ObjectCounter) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, ViewObject.class);
                intent.putExtra("position", position);
                saveInFile();
                startActivity(intent);
            }
        });

    }

    public void addButton(View view) {
        Intent intent1 = new Intent(MainActivity.this, AddObject.class);
        startActivity(intent1);
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
