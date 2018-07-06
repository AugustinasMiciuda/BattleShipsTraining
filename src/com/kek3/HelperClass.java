package com.kek3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by topkek on 2018.07.02.
 */
public class HelperClass {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int [] grid = new int[gridSize];
    private int comCount=0;



    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            inputLine = reader.readLine();
            if (inputLine.length() == 0){
                return null;
            }
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
        return inputLine.toLowerCase();
    }

    public  String randomNumber(){
             int  x =(int)(Math.random()*5)+1;
//            ArrayList<Integer>  ship = new ArrayList<>();
//            int x1= x+1;
//            int x2 = x+2;
//            ship.add(x);
//            ship.add(x1);
//            ship.add(x2);

            return Integer.toString(x);
        }

    private  String randomChar(){
        Random r = new Random();
//        ArrayList<String> array = new ArrayList<>();
        char c = (char)(r.nextInt(7) + 'a');
        return Character.toString(c);
    }

    public ArrayList<String> placeDotCom(){
        ArrayList<String>  shipCoordiantes = new ArrayList<>();
        shipCoordiantes.add(randomChar()+randomNumber());

        return shipCoordiantes;
    }

    public ArrayList<String> placeDotCom2(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphCoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;                                  // current attempts counter
        boolean success = false;                           // ﬂ ag = found a good location ?
        int location = 0;                                  // current starting location

        comCount++;                                        // nth dot com to place
        int incr = 1;                                      // set horizontal increment
        if ((comCount % 2) == 1) {                         // if odd dot com (place vertically)
            incr = gridLength;                               // set vertical increment
        }
        while (!success & attempts++ < 200) {             // main search loop  (32)
            location = (int) (Math.random() * gridSize); // get random starting point
            //System.out.print(“ try “ + location);
            int x = 0;                                   // nth position in dotcom to place
            success = true;                                 // assume success
            while (success && x < comSize) {                // look for adjacent unused spots
                if (grid[location] == 0) {
                    coords[x++] = location;                    // save location
                    location += incr;                          // try ‘next’ adjacent
                    if (location >= gridSize) {                 // out of bounds - ‘bottom’
                        success = false;                         // failure
                    }
                    if (x > 0 && (location % gridLength == 0)) {  // out of bounds - right edge
                        success = false;                         // failure
                    }
                } else {                                      // found already used location
                    // System.out.print(“ used “ + location);
                    success = false;                          // failure
                }
            }
        }                                                   // end while

        int x = 0;                                          // turn location into alpha coords
        int row = 0;
        int column = 0;
        // System.out.println(“\n”);
        while (x < comSize) {
            grid[coords[x]] = 1;                              // mark master grid pts. as ‘used’
            row = (int) (coords[x] / gridLength);             // get row value
            column = coords[x] % gridLength;                  // get numeric column value
            temp = String.valueOf(alphabet.charAt(column));   // convert to alpha

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
             System.out.print("  coord "+x+" = " + alphaCells.get(x-1));
        }

         System.out.println("\n");

        return alphaCells;
    }
}
