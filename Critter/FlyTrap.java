// This defines a simple class of critters that infect whenever they can and
// otherwise just spin around, looking for critters to infect.  This simple
// strategy turns out to be surpisingly successful.
// author: Leo Zheng drleozheng@gmail.com
import java.awt.*;

public class FlyTrap extends Critter {
    
    private int cnt = 0;
    
    public Action getMove(CritterInfo info) {
        cnt++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else {
               if (info.getFront() == Neighbor.EMPTY) {
                  return Action.HOP;
               } else {
                  return Action.LEFT;
               }
        }
        
    }

    public Color getColor() {
        if (cnt % 2 == 0) { 
             return Color.RED;
        }else {
             return Color.BLUE;
        
        }
    }

    public String toString() {
        return "\\";
    }
}