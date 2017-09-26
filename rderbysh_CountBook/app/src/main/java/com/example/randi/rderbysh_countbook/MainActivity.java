package com.example.randi.rderbysh_countbook;

import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private ListView oldObjectsList;

    private ArrayList<ObjectCounter> objects = new ArrayList<ObjectCounter>();

    public static final String EXTRA_MESSAGE = "com.example.randi.rderbysh_countbook.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void addButton(View view) {
        Intent intent1 = new Intent(MainActivity.this, AddObject.class);
        startActivity(intent1);
    }

}
