package com.info.bitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MapActivity extends AppCompatActivity {
    private Toolbar toolbarMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        toolbarMap=findViewById(R.id.toolbarMap);
        toolbarMap.setTitle(R.string.Harita);
        setSupportActionBar(toolbarMap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}