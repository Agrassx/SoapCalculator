package com.agrass.Calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class DisplayAdditionActivity extends Activity {

    String[] data_oils = {"Оливковое", "Растительное", "Облепиховое", "Эвкалиптовое", "Льняное", "Тыквенное", "Кокосовое"};
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterChanged;
    ArrayList<String> OilList;

    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_addition);




            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//             Show the Up button in the action bar.
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }

        Intent intent = new Intent(this, MainActivity.class);
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setPrompt("Масла");//Title
        spinner.setSelection(0);

        if ((savedInstanceState != null)
                && (savedInstanceState.getStringArrayList("f") != null)) {

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    savedInstanceState.getStringArrayList("f"));


        } else {

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_oils);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }



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

            spinner = (Spinner) findViewById(R.id.spinner);

            Intent intent = new Intent(this, MainActivity.class);

            EditText editTextMass = (EditText) findViewById(R.id.editText_Multiplier_First);

            if (editTextMass.getText().toString().isEmpty() == true) {

                Toast.makeText(getBaseContext(), "Укажите массу!", Toast.LENGTH_LONG).show();

            } else {

                String StringMass = editTextMass.getText().toString();

                String valueOfSpinner = spinner.getSelectedItem().toString();

                String ans = valueOfSpinner+" - "+StringMass+" - ";

                intent.putExtra("floatOil", ans);

                setResult(RESULT_OK, intent);

//                ArrayList<String> OilList = new ArrayList<String>();

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // View element position
//                    Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                        adapterChanged = (ArrayAdapter<String>) spinner.getAdapter();
                        adapterChanged.remove(adapterChanged.getItem(position));
                        adapterChanged.notifyDataSetChanged();


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });

                finish();

            }



        }
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        ArrayList<String> saveSpinner = new ArrayList<String>();
        for (int i = 0; i < adapterChanged.getCount(); i++)
            saveSpinner.add(adapterChanged.getItem(i).toString());

        savedInstanceState.putStringArrayList("f",saveSpinner);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);




//        Log.v(TAG, "Inside of onRestoreInstanceState");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                savedInstanceState.getStringArrayList("f"));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_oils);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

}