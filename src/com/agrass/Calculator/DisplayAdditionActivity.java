package com.agrass.Calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class DisplayAdditionActivity extends Activity {

    String[] data_oils = {"Оливковое", "Ростительное", "Облепиховое", "Эвкалиптовое", "млдапдваы"};

    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_addition);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//             Show the Up button in the action bar.
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_oils);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Масла");//Title
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // View element position
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }






        public void buttonAdd(View v) {

            Spinner spinner = (Spinner) findViewById(R.id.spinner);

            Intent intent = new Intent(this, MainActivity.class);

            EditText editTextMultiplierFirst = (EditText) findViewById(R.id.editText_Multiplier_First);
            EditText editTextMultiplierSecond = (EditText) findViewById(R.id.editText_Multiplier_Second);

            String StringMultiplierFirst = editTextMultiplierFirst.getText().toString();
            String StringMultiplierSecond = editTextMultiplierSecond.getText().toString();

            String valueOfSpinner = spinner.getSelectedItem().toString();

            float multiplier_first = Float.valueOf(editTextMultiplierFirst.getText().toString());
            float multiplier_second = Float.valueOf(editTextMultiplierSecond.getText().toString());

            //float[] floatOil = {multiplier_first, multiplier_second};

            String ans = valueOfSpinner+" - "+StringMultiplierSecond+"-"+StringMultiplierFirst;

            intent.putExtra("floatOil", ans);
            //intent.putExtra("ans",ans);
            setResult(RESULT_OK, intent);
            finish();
        }

}