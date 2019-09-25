/* This defines a class of critters named Giant that infect other
types of critters whenever they can, otherwise move around. They 
change display alternatively as "fee","fie","foe","fum" every 6 
moves.The color is set as gray.
author: Leo Zheng drleozheng@gmail.com
*/

import java.awt.*;

public class Giant extends Critter {
    // track the number of movements of critter
    // initialized as zero
    private int step;
    // the display of critter e.g."fee","fie","foe" or "fum"
    private String chosenDisplay;
   
    //constructor
    public Giant() {
        step = 0;
        chosenDisplay = toString();
    }
   
    // define how critter acts
    public Action getMove(CritterInfo info) {
        step++;
        if (info.getFront() == Neighbor.OTHER) {
           // infect if other species in front
           return Action.INFECT;
        } else {
               if (info.getFront() == Neighbor.EMPTY) {
                  //hop if empty space in front
                  return Action.HOP;
               } else {
                  return Action.RIGHT;
               }   
        }
    }
   
    // set the color of critter
    public Color getColor() { 
        return Color.GRAY;
    }
   
    // display as "fee" for first 6 steps
    // change into "fie","foe" and "fum" sequentially every 6 steps
    // repeat the pattern
    public String toString() {
        if ((step + 24) % 24 == 0) {
           chosenDisplay = "fee";
           return chosenDisplay;
        }    
      
        if ((step + 18) % 24 == 0) {
           chosenDisplay = "fie";
           return chosenDisplay;
        } 
      
        if ((step + 12) % 24 == 0) {
           chosenDisplay = "foe";
           return chosenDisplay;
        }
      
        if ((step + 6) % 24 == 0 ) {
           chosenDisplay = "fum";
           return chosenDisplay;
        }
      
        return chosenDisplay;
    }

}