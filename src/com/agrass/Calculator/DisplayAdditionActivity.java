package com.agrass.Calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.Toast.*;

public class DisplayAdditionActivity extends Activity {

    String[] DataOils = {"Оливковое", "Растительное", "Облепиховое", "Эвкалиптовое",
                            "Льняное", "Тыквенное", "Кокосовое"};
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayList<String> OilList = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Create","OnCreate Begin");
        setContentView(R.layout.activity_display_addition);
        spinner = (Spinner) findViewById(R.id.spinner);
        getActionBar().setDisplayHomeAsUpEnabled(false);

        String[] CheckedElements = getIntent().getStringArrayExtra("CheckElements");


        spinner.setPrompt("Масла");//Title
        spinner.setSelection(0);

        OilList.addAll(Arrays.asList(DataOils).subList(0, DataOils.length));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, OilList);

        if (CheckedElements != null)
            for (int i = 0; i < CheckedElements.length; i++) {

                adapter.remove(CheckedElements[i]);
                adapter.notifyDataSetChanged();
            }

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

        if (StringMass.isEmpty()) makeText(getBaseContext(), "Укажите массу!", LENGTH_LONG).show();
        else {

            String ans = valueOfSpinner + "-" + StringMass;
            intent.putExtra("floatOil", ans);

            setResult(RESULT_OK, intent);
            finish();
        }

        Log.e("Add", "ButtonAdd End");


    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }



}