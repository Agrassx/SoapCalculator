package com.agrass.Calculator;

public class StructureOfOils {
    String Name;
    float Mass;

    public void putMassString(String Mass) {

        this.Mass = Float.valueOf(Mass);

    }

    public void putMassFloat(Float Mass) {

        this.Mass = Mass;

    }


    public StructureOfOils(String Row) {

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
