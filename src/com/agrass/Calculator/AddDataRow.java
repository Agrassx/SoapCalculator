package com.agrass.Calculator;
import android.widget.ArrayAdapter;
import java.util.ArrayList;


/**
 * Created by Agrass on 01.03.14.
 */
public class AddDataRow {

    public ArrayList<String> arraylist;
    public int count;
    public String d[] = new String[20]; // ???!!! must "… = new String[count]"
    public ArrayAdapter<String> adapter;

    public AddDataRow(ArrayAdapter<String> PutAdapter) {

        adapter = PutAdapter;
        count = PutAdapter.getCount();

    }


    public String[] getString() {

        count = adapter.getCount();
        float sum = 0;

            for (int i = 0; i < adapter.getCount(); i++) {

                String[] item = adapter.getItem(i).split(" - ");
                Float item_float2 = Float.valueOf(item[1]);

                sum += item_float2;

            }

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
