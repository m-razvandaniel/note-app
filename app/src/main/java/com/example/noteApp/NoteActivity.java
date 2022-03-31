package com.example.noteApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.example.noteApp.tools.DataObject;
import com.google.android.material.textfield.TextInputEditText;

public class NoteActivity extends AppCompatActivity {
    //public int n = 0;
    public int tempN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextInputEditText textInputEditText1 = findViewById(R.id.titleNoteInput);
        TextInputEditText textInputEditText2 = findViewById(R.id.textNoteInput);

        //n = n + 1;
        Intent intent = getIntent();
        String operation = intent.getStringExtra(MainActivity.INTENT_NOTE_OPERATION);   //get the type of operation

        if(operation.contentEquals("access")) {
            int code = intent.getIntExtra(MainActivity.INTENT_ID, -1);  //get the id if the operation is ACCESS
            for(Note temp : MainActivity.note) {
                if(temp.code == code) {
                    this.tempN = MainActivity.note.indexOf(temp);
                }
            }

            textInputEditText1.setText(MainActivity.note.get(tempN).noteTitle);
            textInputEditText2.setText(MainActivity.note.get(tempN).noteText);
        }

        DataObject dataObject = new DataObject();
        //save button
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(operation.contentEquals("create")) {
                    MainActivity.note.add(new Note(generateId(), textInputEditText1.getText().toString(), textInputEditText2.getText().toString()));
                    MainActivity.noteListAdapter.notifyDataSetChanged();
                }
                if(operation.contentEquals("access")) {
                    Note noteTemp = MainActivity.note.get(tempN);
                    noteTemp.noteTitle = textInputEditText1.getText().toString();
                    noteTemp.noteText = textInputEditText2.getText().toString();
                    MainActivity.noteListAdapter.notifyDataSetChanged();
                }
                //HashSet<Note> hashSet = new HashSet<Note>(MainActivity.note);

                dataObject.addData();
                finish();
            }
        });
    }

    private int generateId() {
        MainActivity.plusN();
        return MainActivity.n;
    }
}