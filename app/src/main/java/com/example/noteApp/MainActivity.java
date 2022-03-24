package com.example.noteApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Note> note = new ArrayList<>();
    public static RecyclerView.Adapter noteListAdapter;
    public static final String INTENT_NOTE_OPERATION = "com.example.simplenote.MainActivity.1";
    public static final String INTENT_ID = "com.example.simplenote.MainActivity.2";
    public static int n = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //note.add(new Note(0, "Note Title", "Note Text"));

        Gson gson = new Gson();
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_string), Context.MODE_PRIVATE);

        if(sharedPreferences.contains("arrayList")) {
            Type collection = new TypeToken<ArrayList<Note>>(){
            }.getType();
            String json = sharedPreferences.getString("arrayList", "0");
            note = gson.fromJson(json, collection);
            n = sharedPreferences.getInt("n", -1);
        }

        //setting the reciclerView
        noteListAdapter = new NoteListAdapter(note);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);

        Intent intent = new Intent(this, NoteActivity.class);
        //intent.putExtra(INTENT_NOTE_OPERATION, "create");
        ExtendedFloatingActionButton extendedFloatingActionButton = findViewById(R.id.extended_fab);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(INTENT_NOTE_OPERATION, "create");
                startActivity(intent);
            }
        });

        /*RecyclerView.Adapter noteListAdapter = new NoteListAdapter(note.toArray());
        RecyclerView recyclerView = new RecyclerView(R.id.recyclerView);
        recyclerView.setAdapter(noteListAdapter);*/
    }

    public static void plusN() {
        n = n + 1;
    }
}