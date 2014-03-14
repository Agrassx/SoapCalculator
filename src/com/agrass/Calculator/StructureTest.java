package com.agrass.Calculator;

public class StructureTest {
    String Name;
    float Mass;

    public StructureTest(String Row) {

        String[] item = Row.split("-");

        Name = item[0];
        Mass = Float.valueOf(item[1]);


    }

    public String getName() {
        return Name;
    }

    public float getMass() {
        return Mass;
    }

}
