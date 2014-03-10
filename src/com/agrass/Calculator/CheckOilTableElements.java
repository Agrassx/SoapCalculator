package com.agrass.Calculator;

import android.widget.ArrayAdapter;

public class CheckOilTableElements {

    public ArrayAdapter<String> adapter;

    public CheckOilTableElements(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;

    }


    public String[] getStringArray() {

        String[] StringArray = new String[adapter.getCount()];


            for (int i = 0; i < adapter.getCount(); i++) {
                    String[] item = adapter.getItem(i).split(" - ");
                    StringArray[i] = item[0];
            }

        return StringArray;

    }


}
