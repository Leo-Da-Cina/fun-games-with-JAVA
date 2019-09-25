/* this defines a class of critters named Lion. They can infect
other types of critters whenever they can. Lion is displayed as
"L". Every 3 steps, each critter randomly pick up a color btw
red, green and blue.
 author: Leo Zheng, drleozheng@gmail.com
*/

import java.awt.*;
import java.util.*;

public class Lion extends Critter {
    //track number of movements of critter   
    private int step;
    //define random number generator for chosing color
    private Random rand = new Random();
    private int die;
    // color assigned to Lion critter
    private Color chosenColor;
   
    //constructor
    public Lion() {
        step = 0;
        chosenColor = getColor();
    }
   
    //action guide for Lion
    public Action getMove(CritterInfo info) {
        step++;
        if (info.getFront() == Neighbor.OTHER) {
           //infect if other species in front
            return Action.INFECT;
        } else {
               if ((info.getFront() == Neighbor.WALL) 
                   | info.getRight() == Neighbor.WALL ) {
                  // turn left if the Wall is in front or on right
                   return Action.LEFT;
               } else {
                      if (info.getFront() == Neighbor.SAME) {
                          //turn left if bump into other lion
                          return Action.LEFT;
                      } else {
                          //otherwise hop
                          return Action.HOP;
                      }
            
               }
        }   
    }
    // randomly pick btw red, green and blue every 3 moves
    public Color getColor() {
       
        if (step % 3 == 0) {
            die = rand.nextInt(3);
            if (die == 0) {
                chosenColor = Color.RED;
                return chosenColor;
            } else {
                   if (die == 1) {
                       chosenColor = Color.GREEN;
                       return chosenColor;
                   } else {
                        chosenColor = Color.BLUE;
                        return chosenColor;
                   }
            }
        }  
        return chosenColor;  
    }
    //display as "L"
    public String toString() {
        return "L";
    }

}