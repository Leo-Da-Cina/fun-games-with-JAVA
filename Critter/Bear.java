/* This defines a class of critters named Bear. They can infect other 
types of critters whenever they can. There are two types of bears, white 
polar bears and black black bears. The choice is made by the client code 
by passing a booleanto the class constructor. Bears display alternatively
as "/" and "\" after each move 

author: Leo Zheng drleozheng@gmail.com

*/

import java.awt.*;

public class Bear extends Critter {
    // track the number of movements of bears
    private int step;
    // variable for choosing polar or black bear
    private boolean polar;
   
    //constructor
    public Bear(boolean initialpolar) {
        polar = initialpolar;
        step = 0;
    }
   
    //Action guide for bears
    public Action getMove(CritterInfo info) {
        step++;
       
        if (info.getFront() == Neighbor.OTHER) {
            //infect if it's other species in front
            return Action.INFECT;
        } else {
              // hop for one square if it's empty in front
              // otherwise turn left
              if (info.getFront() == Neighbor.EMPTY) {
                  return Action.HOP;
              } else {
                   return Action.LEFT;   
              }    
        }  
    }  
   
    // white color for polar bears and black for black bears
    public Color getColor() {
        if (polar) {
           return Color.WHITE;
        } else {
           return Color.BLACK; 
        } 
    }
   
    // alternate the display btw "/" and "\"
    public String toString() {
        if (step % 2 == 0) {
           return "/";
        } else {
           return "\\";
        }      
    }

}