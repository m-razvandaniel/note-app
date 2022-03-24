package com.example.noteApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

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
        String operation = intent.getStringExtra(MainActivity.INTENT_NOTE_OPERATION);
        int code = intent.getIntExtra(MainActivity.INTENT_ID, -1);

        if(operation.contentEquals("access")) {
            for(Note temp : MainActivity.note) {
                if(temp.code == code) {
                    this.tempN = MainActivity.note.indexOf(temp);
                }
            }

            textInputEditText1.setText(MainActivity.note.get(tempN).noteTitle);
            textInputEditText2.setText(MainActivity.note.get(tempN).noteText);
        }

        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(operation.contentEquals("create")) {
                    MainActivity.note.add(new Note(MainActivity.n + 1, textInputEditText1.getText().toString(), textInputEditText2.getText().toString()));
                    MainActivity.plusN();
                    MainActivity.noteListAdapter.notifyDataSetChanged();
                }
                if(operation.contentEquals("access")) {
                    Note noteTemp = MainActivity.note.get(tempN);
                    noteTemp.noteTitle = textInputEditText1.getText().toString();
                    noteTemp.noteText = textInputEditText2.getText().toString();
                }
                //HashSet<Note> hashSet = new HashSet<Note>(MainActivity.note);
                Gson gson = new Gson();
                String json = gson.toJson(MainActivity.note);

                Context context = getApplicationContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.preference_file_string), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("arrayList", json);
                editor.putInt("n", MainActivity.n);
                editor.apply();

                finish();
            }
        });
    }
}