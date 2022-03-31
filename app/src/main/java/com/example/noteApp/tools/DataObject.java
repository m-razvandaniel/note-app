package com.example.noteApp.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.noteApp.MainActivity;
import com.example.noteApp.Note;
import com.example.noteApp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataObject {
    public void initializeArrayList() {
        Gson gson = new Gson();
        Context context = MyApplication.generalContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_string), Context.MODE_PRIVATE);
        //sharedPreferences.edit().clear().commit();

        if(sharedPreferences.contains("arrayList")) {
            Type collection = new TypeToken<ArrayList<Note>>(){
            }.getType();
            String json = sharedPreferences.getString("arrayList", "0");
            MainActivity.note = gson.fromJson(json, collection);
            MainActivity.n = sharedPreferences.getInt("n", -1);
        }
    }

    public void updateData() {
        Gson gson = new Gson();
        Context context = MyApplication.generalContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_string), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("arrayList", gson.toJson(MainActivity.note)).apply();
    }

    public void addData() {
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.note);

        Context context = MyApplication.generalContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_string), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("arrayList", json);
        editor.putInt("n", MainActivity.n);
        editor.apply();
    }
}