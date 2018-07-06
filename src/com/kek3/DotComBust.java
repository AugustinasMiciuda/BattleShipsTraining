package com.kek3;

import java.util.ArrayList;

/**
 * Created by topkek on 2018.07.02.
 */
public class DotComBust {

    private DotCom dotCom;
    private HelperClass helperClass = new HelperClass();
    private ArrayList<DotCom> shipList = new ArrayList<>();
    private int numOfGuesses=0;


    private void setUp() {
        for (int i = 0; i < 3; i++) {
            dotCom = new DotCom(helperClass.placeDotCom2(3), helperClass.getUserInput("Enter Ship name"));
            shipList.add(dotCom);

        }
    }

    public void startPlaying(){
        setUp();
        boolean end = false;
        while (end == false){
            String input =  helperClass.getUserInput("Please enter your guess");
            if (!shipList.isEmpty()){
                checkUserGuess(input);
                numOfGuesses++;
            }
            if (shipList.isEmpty()){

                finishGame();
                end =true;
            }
        }


    }

    private void checkUserGuess(String input){
        String result = null;
        for (DotCom ship : shipList){
             result = ship.checkYourself(input);
            if (result.contains("kill")){
                shipList.remove(ship);
            }
             if (!result.contains("miss") ){
                 break;
             }

        }
        System.out.println(result);
    }

    private void finishGame(){
        if (numOfGuesses < 10){
            System.out.println("WOW, you've WON, and it only took " + numOfGuesses + " guesses");
        } else if ( (10 >= numOfGuesses) && (numOfGuesses < 15) ){
            System.out.println("You did great, you've won with " + numOfGuesses + " guesses");
        } else if (numOfGuesses >= 15){
            System.out.println("Wow, it's a miracle you've won, you sucked so hard you needed " + numOfGuesses + " guesses");
        }


    }
}
