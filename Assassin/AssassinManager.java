//author: Leo Zheng drleozheng@gmail.com
//Nov 5th ,2015

/*This AssassinManager class is to work with AssassinMain to play the 
killing game. Given a list of people represented by the nodes in a list.
each node is the killer that kills next node. Each time user assigns a 
person to be killed via console input. The routine will remove this 
person from alive list aka kill ring list and insert it in the front of
dead persons list aka graveyard list. Both the lists will be printed for
the user. When Game is over, the winner and the summary of killing history
will be reported.

Class details is as follows. 
1) Fields:
  two fields of AssassinNode type. One points to the front of kill
  ring list, the other points to the front of graveyard list.
2) constructor: 
  A constructor is provided to initialize the fields based on the list of 
people given by AssassinMain.
3) Method kill():
   kill() is the most important method in this class. Every time the user 
  assigns a person to be killed, the method will store the killer's name in
  the field of the dead person node and move that kilednnode from the kill 
  ring list to the front of graveyard list without changing the order of 
  remaining nodes in the kill ring list. 
4) Other auxiliary methods:
  isGameOver() returns true when game is over
  printKillRing() and printGraveyard() print the content of kill ring list 
  and graveyard list as well as the relationship btw nodes
  killRingContains(String name) and graveyardContains(String name) check if 
  the given name is in either list.
  winner() reports the winner when game is over
  listContains(...) and getLastNode(...) are two only private methods. The 
  former checks if a given name is in the list and is the main body of 
  killRingContains(...) and graveyardContains(...). The latter returns the
  last node a list. 
*/

import java.util.*;

public class AssassinManager {
   private AssassinNode frontKillRing;
   private AssassinNode frontGraveyard;
   //constructor 
   public AssassinManager(List<String> names) {
      //throw exception if names is either empty or null
      //all assume names has no duplicate nodes
      if (names.isEmpty() || names == null ) {
         throw new IllegalArgumentException("List can't be empty or null !");
       } 
      // initialize kill ring list
      // list front points to first element of names
      frontKillRing = new AssassinNode(names.get(0));
      AssassinNode current = frontKillRing;
      // sequentially adding elements of names to list nodes
      for(int i = 1 ; i < names.size(); i++) {
         current.next  = new AssassinNode(names.get(i));
         current = current.next;
      }
      // initialize graveyard list.
      frontGraveyard = null; 
   }
   
   // print everybody's names in the current kill ring list 
   // as well as the stalking relationship btw nodes
   public void printKillRing() {
      AssassinNode current = frontKillRing;
      if (isGameOver()) {       
         System.out.println("    " + current.name 
                            + " is stalking " + current.name);
         
      } else {   
             while (current.next != null) {
             System.out.println("    " + current.name 
                                + " is stalking " + current.next.name);
             current = current.next;
      }
             System.out.println("    " + current.name 
                                + " is stalking " + frontKillRing.name);
      }
   }

   // print every dead person's name as well as who killed whom
   // recently killed person on the top
   public void printGraveyard() {
      if (frontGraveyard != null) {
         AssassinNode current  = frontGraveyard;
         while(current != null) {
            System.out.println("    " + current.name 
                               + " was killed by " + current.killer); 
            current = current.next;  
         }
      
      }
   }
   
   // test if given name (a String) is in the list
   // AssassinNode current points to the front of the list
   // return true if name is in the list, otherwise false
   private boolean listContains(String name, AssassinNode current) {
      while(current != null) {
         if (name.equalsIgnoreCase(current.name)) {
            return true;
         }
         current = current.next;
      }   
      return false;   
   }
   
   // given the front of a list,return the last node in the list
   private AssassinNode getLastNode(AssassinNode front) {
      AssassinNode current = front;
      while(current.next != null) {
         current = current.next;
      }
      return current;
   }
   
   // test if given name is in the kill ring list
   // input is string type name, return true if it's in the 
   // list, otherwise false returned
   public boolean killRingContains(String name) {
      AssassinNode current = frontKillRing;
      return listContains(name,current);
   }
   
   // test if given name is in the graveyard list
   // input is string type name, return true if it's in the 
   // list, otherwise false 
   public boolean graveyardContains(String name) {
      AssassinNode current = frontGraveyard;
      return listContains(name,current);  
   }
   
   // return true if game is over
   // if only one person in the kill ring list, game is over
   public boolean isGameOver() {
      if (frontKillRing.next == null) {
         return true;
      }
      return false;
   }
   
   // return the name of winner if game is over
   // and null otherwise
   public String winner() {
      String winner = "";
      if (isGameOver()) {
         return winner = frontKillRing.name;
      }
      return winner = null;
   }
   
   // move the node of person to be killed from kill ring list
   // to the front of graveyard list reserving the order of
   // remaining nodes in the kill ring list
   // this method accepts name of string type, void return
   public void kill(String name) {
      // if unknow name is given throws exception
      if (isGameOver()) {
         throw new IllegalStateException("Game is over !");
      }
      // if that person was already killed , throw an exception
      if (!killRingContains(name)) {
         throw new IllegalArgumentException("name is not in the list!");
      }
      
      // refers to the to-be-killed person node , initially points to the
      // front of kill ring list
      AssassinNode dead = frontKillRing;
      // refers to killer node, initially null
      AssassinNode assassin = null;
      // refers to last node in the kill ring list
      AssassinNode backKillRing = getLastNode(frontKillRing);
      
      if (name.equalsIgnoreCase(dead.name)) {
         //if the front person is to be killed, the last node is killer 
         dead.killer = backKillRing.name;
         //remove the killed person node from the kill ring list
         frontKillRing = frontKillRing.next;
         //insert the killed person node in the front of Graveyard list
         dead.next = frontGraveyard;
         frontGraveyard = dead;
      } else {
             //loop through list finding killer and dead person
             //by moving two pointers in parallel, dead for the person
             // to be killed, assassin for the killer
             while(!name.equalsIgnoreCase(dead.name)) {
               assassin = dead;
               dead = dead.next; 
             }
             
             dead.killer = assassin.name;
             if (name.equalsIgnoreCase(backKillRing.name)) {
             // case that the to-be-killed person is in the last node
               assassin.next = null;
             } else {
             //case that to-be-killed person in the middle
               assassin.next = dead.next;
             }
             //insert killed node to the front of graveyard list
             dead.next = frontGraveyard;
             frontGraveyard = dead; 
                                 
      }
   
   }


}
