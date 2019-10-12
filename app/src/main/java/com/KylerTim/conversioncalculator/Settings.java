package com.KylerTim.conversioncalculator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {

    private String fromSelection;
    private String toSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("fromSelectionChoice", fromSelection);
                setResult(MainActivity.FROM_SELECTION, intent);
                intent.putExtra("toSelectionChoice", toSelection);
                setResult(MainActivity.TO_SELECTION, intent);
                finish();
            }
        });

        Spinner fromSpinner = (Spinner) findViewById(R.id.fromUnitChoice);
        Spinner toSpinner = (Spinner) findViewById(R.id.toUnitChoice);

        ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.LengthChoices, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> volumeAdapter = ArrayAdapter.createFromResource(this,
                R.array.VolumeChoices, android.R.layout.simple_spinner_item);

        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(lengthAdapter);
        fromSpinner.setAdapter(lengthAdapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromSelection = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toSelection = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
