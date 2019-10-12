package com.KylerTim.conversioncalculator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {


    private String fromSelection = "Yards";
    private String toSelection = "Meters";
    private int modeSelection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent resultIntent = new Intent();
                resultIntent.putExtra("fromSelectionChoice", fromSelection);
                resultIntent.putExtra("toSelectionChoice", toSelection);
                setResult(MainActivity.FROM_SELECTION, resultIntent);
                setResult(MainActivity.TO_SELECTION, resultIntent);
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

        if(modeSelection == 0){
            fromSpinner.setAdapter(lengthAdapter);
            toSpinner.setAdapter(lengthAdapter);
        }
        else if(modeSelection == 1){
            fromSpinner.setAdapter(volumeAdapter);
            toSpinner.setAdapter(volumeAdapter);
        }

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
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == importedFrom) {
//            fromSelection = data.getStringExtra("fromUnits");
//        }
//        if (resultCode == importedTo){
//            toSelection = data.getStringExtra("toUnits");
//        }
//        if(resultCode == importedMode){
//            modeSelection = data.getIntExtra("mode", 0);
//        }
//    }

}
