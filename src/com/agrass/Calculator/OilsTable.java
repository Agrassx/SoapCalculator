package com.agrass.Calculator;

import java.util.ArrayList;

public class OilsTable {

//    public ArrayAdapter<String> adapter;
    public ArrayList<StructureOfOils> arrayList = new ArrayList<StructureOfOils>();

    public OilsTable(ArrayList<StructureOfOils> arrayList) {

//        adapter = PutAdapter;
        this.arrayList = arrayList;

    }


    public String[] getRows() {

        int count = arrayList.size() + 1;
        String Table[] = new String[count];
        SumOfOilsMass SumOfMass = new SumOfOilsMass(arrayList);
        float sum = SumOfMass.getSum();



        for (int i = 0; i < arrayList.size(); i++) {

//            String[] item = adapter.getItem(i).split(" - ");
//            String StringOil = item[0];
//            Float itemMass = Float.valueOf(item[1]);
            float item_percent = Math.round((arrayList.get(i).getMass()/sum)*10000);
            Table[i] =  arrayList.get(i).getName() + " - " + Float.toString(arrayList.get(i).getMass())
                    + " - " + Float.toString(item_percent / 100) + "%";

        }

        return Table;

    }

}
