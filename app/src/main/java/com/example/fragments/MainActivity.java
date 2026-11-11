package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // parses recipes.json into JSONArray
    public static JSONArray readJSON (Context context){
        try {
            Scanner scanner = new Scanner(context.getAssets().open("recipes.json"));
            String json = "";
            while (scanner.hasNextLine()) {
                json += scanner.nextLine();
            }
            scanner.close();
            return new JSONArray(json);
        } catch (Exception e) {
            return new JSONArray();
        }
    }
}