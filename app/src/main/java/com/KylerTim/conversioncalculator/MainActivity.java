package com.KylerTim.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    public static final int FROM_SELECTION = 1;
    public static final int TO_SELECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleText = (TextView) findViewById(R.id.titleText);

        TextView fromUnitLabel = (TextView) findViewById(R.id.fromUnitLabel );
        TextView toUnitLabel = (TextView) findViewById(R.id.toUnitLabel);

        EditText fromTextEdit = (EditText) findViewById(R.id.fromTextEdit);
        EditText toTextEdit = (EditText) findViewById(R.id.toTextEdit);

        Button calculateButton = (Button) findViewById(R.id.calculateButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button modeButton = (Button) findViewById(R.id.modeButton);

        //if mode is 0: length, else volume.
        AtomicInteger mode = new AtomicInteger();

        clearButton.setOnClickListener(y -> {
            hideSoftKeyBoard();
            fromTextEdit.setText("");
            toTextEdit.setText("");
        });

        calculateButton.setOnClickListener(v -> {
            hideSoftKeyBoard();
            String fromInput = fromTextEdit.getText().toString();
            String toInput = toTextEdit.getText().toString();

            if(fromInput.length() == 0 && toInput.length() == 0){
                Snackbar.make(fromTextEdit,"No info entered.", Snackbar.LENGTH_LONG).show();
            }

            if(fromInput.length() != 0 && toInput.length() == 0){
                if(mode.get() == 0) {
                    double toAnswer = UnitsConverter.convert(Double.parseDouble(fromInput), UnitsConverter.LengthUnits.valueOf(fromUnitLabel.getText().toString()), UnitsConverter.LengthUnits.valueOf(toUnitLabel.getText().toString()));
                    toTextEdit.setText(String.valueOf(toAnswer));
                } else {
                    double toAnswer = UnitsConverter.convert(Double.parseDouble(fromInput), UnitsConverter.VolumeUnits.valueOf(fromUnitLabel.getText().toString()), UnitsConverter.VolumeUnits.valueOf(toUnitLabel.getText().toString()));
                    toTextEdit.setText(String.valueOf(toAnswer));
                }
            }

            if(toInput.length() != 0 && fromInput.length() == 0){
                if (mode.get() == 0) {
                    double fromAnswer = UnitsConverter.convert(Double.parseDouble(toInput), UnitsConverter.LengthUnits.valueOf(toUnitLabel.getText().toString()), UnitsConverter.LengthUnits.valueOf(fromUnitLabel.getText().toString()));
                    fromTextEdit.setText(String.valueOf(fromAnswer));
                } else {
                    double fromAnswer = UnitsConverter.convert(Double.parseDouble(toInput), UnitsConverter.VolumeUnits.valueOf(toUnitLabel.getText().toString()), UnitsConverter.VolumeUnits.valueOf(fromUnitLabel.getText().toString()));
                    fromTextEdit.setText(String.valueOf(fromAnswer));
                }
            }

            if(toInput.length() != 0 && fromInput.length() != 0){
                Snackbar.make(fromTextEdit,"Both inputs are full.",Snackbar.LENGTH_LONG).show();
            }
        });

        modeButton.setOnClickListener(v ->{
            hideSoftKeyBoard();
            fromTextEdit.setText("");
            toTextEdit.setText("");
            if(mode.get() == 0) {
                mode.set(1);
                fromUnitLabel.setText("Gallons");
                toUnitLabel.setText("Liters");
                titleText.setText("Volume Converter");
            }
            else{
                mode.set(0);
                fromUnitLabel.setText("Yards");
                toUnitLabel.setText("Meters");
                titleText.setText("Length Converter");
            }
        });


    }

    //https://stackoverflow.com/questions/3553779/android-dismiss-keyboard
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
