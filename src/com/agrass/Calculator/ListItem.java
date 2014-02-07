package com.agrass.Calculator;

/**
 * Created by Agrass on 07.02.14.
 */
public class ListItem {

    public float[] multiplier = new float[2];



    public ListItem(float[] m) {

        multiplier = m;

    }

    public String toString() {

        return multiplier[0] + " x " + multiplier[1];

    }

}
