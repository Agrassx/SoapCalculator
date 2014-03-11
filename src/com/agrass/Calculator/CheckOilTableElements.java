package com.agrass.Calculator;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CheckOilTableElements {

    public ArrayAdapter<String> adapter;

    public CheckOilTableElements(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;

    }


    public String[] getStringArray() {

        String[] StringArray = new String[adapter.getCount()];

            for (int i = 0; i < adapter.getCount(); i++) {

                    String[] item = adapter.getItem(i).split(" - ");
                    String StringOil = item[0];
                    StringArray[i] = StringOil;
            }

        return StringArray;

    }


}
