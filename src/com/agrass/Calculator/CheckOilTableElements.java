package com.agrass.Calculator;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CheckOilTableElements {

    public ArrayAdapter<String> adapter;

    public CheckOilTableElements(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;

    }


    public String[] getStringArray() {

        String[] StringArray = new String[adapter.getCount() + 1];

            for (int i = 0; i < adapter.getCount(); i++) {

                    String[] item = adapter.getItem(i).split(" - ");
                    StringArray[i] = item[0];
            }

        return StringArray;

    }


}
