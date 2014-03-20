package com.agrass.Calculator;

public class Oil {
    String Name;
    float Mass;

    public void putMass(String Mass) {

        this.Mass = Float.valueOf(Mass);

    }

    public void putMass(Float Mass) {

        this.Mass = Mass;

    }


    public Oil(String Row) {

        String[] item = Row.split(";");

        Name = item[0];
        Mass = Float.valueOf(item[1]);

    }

    public String toString() { return Name + ";" + Mass; }

    public String getName() {
        return Name;
    }

    public float getMass() {
        return Mass;
    }




}
