package com.agrass.Calculator;

import java.util.ArrayList;

public class OilsTable {

    public ArrayList<StructureOfOils> arrayList = new ArrayList<StructureOfOils>();

    public OilsTable(ArrayList<StructureOfOils> arrayList) {

        this.arrayList = arrayList;

    }


    public String[] getRows() {

        int count = arrayList.size() + 1;
        String Table[] = new String[count];
        SumOfOilsMass SumOfMass = new SumOfOilsMass(arrayList);
        float sum = SumOfMass.getSum();



        for (int i = 0; i < arrayList.size(); i++) {

            float item_percent = Math.round((arrayList.get(i).getMass()/sum)*10000);
            Table[i] =  arrayList.get(i).getName() + " - " + Float.toString(arrayList.get(i).getMass())
                   +  " - " + Float.toString(item_percent / 100) + "%";

        }

        return Table;

    }

}
