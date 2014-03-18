package com.agrass.Calculator;

import java.util.ArrayList;

public class SumOfOilsMass {

    public ArrayList<Oil> arrayList = new ArrayList<Oil>();

    public SumOfOilsMass(ArrayList<Oil> arrayList) {

        this.arrayList = arrayList;

    }


    public float getSum() {

        float sum = 0;

        for (int i = 0; i < arrayList.size(); i++) sum += arrayList.get(i).getMass();

        return sum;
    }
}
