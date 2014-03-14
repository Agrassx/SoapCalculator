package com.agrass.Calculator;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CheckOilTableElements {

    public ArrayAdapter<String> adapter;
    public ArrayList<StructureOfOils> arrayList = new ArrayList<StructureOfOils>();

    public CheckOilTableElements(ArrayList<StructureOfOils> arrayList) {

        //adapter = PutAdapter;
        this.arrayList = arrayList;

    }


    public String[] getStringArray() {

        String[] StringArray = new String[arrayList.size()];

            for (int i = 0; i < arrayList.size(); i++) {

                    String NameOfOil = arrayList.get(i).getName();
                    StringArray[i] = NameOfOil;
            }

        return StringArray;

    }


}
