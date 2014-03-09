package com.agrass.Calculator;

import android.widget.ArrayAdapter;

public class SumOfOilsMass {

    public ArrayAdapter<String> adapter;

    public SumOfOilsMass(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;

    }


    public float getSum() {

        float sum = 0;

        for (int i = 0; i < adapter.getCount(); i++) {

            String[] item = adapter.getItem(i).split(" - ");
            Float item_float2 = Float.valueOf(item[1]);

            sum += item_float2;

        }

        return sum;
    }
}
