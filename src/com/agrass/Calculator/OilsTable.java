package com.agrass.Calculator;
import android.widget.ArrayAdapter;


/**
 * Created by Agrass on 01.03.14.
 */
public class OilsTable {

    public ArrayAdapter<String> adapter;

    public OilsTable(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;

    }


    public String[] getRows() {

        int count = adapter.getCount() + 1;
        String d[] = new String[count];
        SumOfOilsMass SumOfMass = new SumOfOilsMass(adapter);
        float sum = SumOfMass.getSum();

        for (int i = 0; i < adapter.getCount(); i++) {

            String[] item = adapter.getItem(i).split(" - ");

            String StringOil = item[0];

            Float itemMass = Float.valueOf(item[1]);

            float item_percent = Math.round((itemMass/sum)*10000);

            d[i] =  StringOil + " - " + item[1] + " - " + Float.toString(item_percent / 100) + "%";

        }

        return d;

    }

}
