package com.agrass.Calculator;

import java.util.ArrayList;

public class CheckOilTableElements {

    ArrayList<Oil> arrayList = new ArrayList<Oil>();

    public CheckOilTableElements(ArrayList<Oil> arrayList) {

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
