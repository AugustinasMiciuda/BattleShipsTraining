package com.kek3;

import java.util.ArrayList;

/**
 * Created by topkek on 2018.07.02.
 */
public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public DotCom(ArrayList<String> locationCells, String name) {
        this.locationCells = locationCells;
        this.name = name;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String getName() {
        return name;
    }

    public String checkYourself(String userInput) {

//        System.out.println(locationCells.iterator().next());
            String result = null;
            int index = locationCells.indexOf(userInput);
            if (index >= 0) {
                result = "hit";
                locationCells.remove(index);
            } else {
                result = "miss";
            }

            if (locationCells.isEmpty()){
                    result = "kill";
                }
        return  result;
    }
}
