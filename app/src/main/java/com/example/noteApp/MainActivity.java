package com.example.noteApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteApp.tools.DataObject;
import com.example.noteApp.tools.NoteListAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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

        //get data from sharedPreferences
        DataObject dataObject = new DataObject();
        dataObject.initializeArrayList();

        //setting the recyclerView
        noteListAdapter = new NoteListAdapter(note);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);

        //clickListener for floating button
        Intent intent = new Intent(this, NoteActivity.class);
        ExtendedFloatingActionButton extendedFloatingActionButton = findViewById(R.id.extended_fab);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(INTENT_NOTE_OPERATION, "create");
                startActivity(intent);
            }
        });

        //funtionality for deleting a note
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                note.remove(viewHolder.getAdapterPosition());
                noteListAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                dataObject.updateData();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    //method to increase the id n on every note created
    public static void plusN() {
        n = n + 1;
    }
}