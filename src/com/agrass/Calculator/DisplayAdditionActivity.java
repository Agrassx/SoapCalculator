package com.agrass.Calculator;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayAdditionActivity extends Activity {

    String[] data_oils = {"Оливковое", "Растительное", "Облепиховое", "Эвкалиптовое",
                            "Льняное", "Тыквенное", "Кокосовое"};
    ArrayList<String> data_oil = new ArrayList<String>();
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayList<String> OilListSave;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Create","OnCreate Begin");
        setContentView(R.layout.activity_display_addition);
        spinner = (Spinner) findViewById(R.id.spinner);
        Intent intent = new Intent(this, MainActivity.class);
        getActionBar().setDisplayHomeAsUpEnabled(false);


        spinner.setPrompt("Масла");//Title
        spinner.setSelection(0);

        for (int i = 0; i < data_oils.length - 1; i++)
            data_oil.add(i, data_oils[i]);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_oil);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Log.e("Create","OnCreate End");

    }

    public void buttonAdd(View view) {

        Log.e("Add","ButtonAdd Begin");

        spinner = (Spinner) findViewById(R.id.spinner);

        final Intent intent = new Intent(this, MainActivity.class);
        EditText editTextMass = (EditText) findViewById(R.id.editText_Multiplier_First);
        String valueOfSpinner = spinner.getSelectedItem().toString();
        String StringMass = editTextMass.getText().toString();

        if ( StringMass.isEmpty() == true ) {

            Toast.makeText(getBaseContext(), "Укажите массу!", Toast.LENGTH_LONG).show();

        } else {

            String ans = valueOfSpinner + " - " + StringMass + " - ";
            intent.putExtra("floatOil", ans);

            setResult(RESULT_OK, intent);
            finish();
        }

        Log.e("Add", "ButtonAdd End");


    }

    public void buttonDel(View view){

        String delitem = spinner.getSelectedItem().toString();
        Toast.makeText(getBaseContext(), delitem+": Was Delete!", Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> deleteItem = (ArrayAdapter<String>) spinner.getAdapter();
        deleteItem.remove(delitem);
        deleteItem.notifyDataSetChanged();
        spinner.setAdapter(deleteItem);

        }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);


        ArrayAdapter<String> adapterChanged = (ArrayAdapter<String>) spinner.getAdapter();
        ArrayList<String> saveSpinner = new ArrayList<String>();

        for (int i = 0; i < adapterChanged.getCount(); i++) {
            if (adapterChanged.getItem(i) != null)
                saveSpinner.add(i, adapterChanged.getItem(i).toString());
        }

        savedInstanceState.putStringArrayList("SavedList", saveSpinner);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        data_oil = savedInstanceState.getStringArrayList("SavedList");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_oil);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



}