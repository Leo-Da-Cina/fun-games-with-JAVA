//author: Leo Zheng, drleozheng@gmail.com
//Dec 2nd 2015


import java.util.*;
import java.io.*;

//this class is to work with QuestionsMain.java to play 20-questions Game
public class QuestionsGame {
   //field
   private QuestionNode questionTree;
   
   //constructor for a leaf node
   public QuestionsGame(String object){
      questionTree = new QuestionNode(object);
   
   }
   //constructor 
   //pre: Scanner input is not null and is attached to a standard file 
   public QuestionsGame(Scanner input){
      //initialize questionTree by using content stored in input
      this.questionTree = this.readTree(input);
   }
  
   //store content in Scanner input as nodes in a pre-order treee
   private QuestionNode readTree(Scanner input){
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode current = new QuestionNode(data);
      if(!type.equals("A:")) {
         current.left = readTree(input);
         current.right = readTree(input);
      }
      return current;
   }

   //store current questions tree to an output file represented by 
   //given PrintStream, an IllegalArgumentException will be thrown
   //if PrintStream output is null 
   public void saveQuestions(PrintStream output){
      if (output == null) {
         throw new IllegalArgumentException();
      }
      QuestionNode current = questionTree;
      writeTree(current,output);   
   }
   
   //print current question tree in preorder to an outfile represented
   // by given PrintStream
   private void writeTree(QuestionNode current,PrintStream output){
      if (current != null) {
         //assign type, 'Q' or 'A'
         String type = "";
         if (current.left == null && current.right == null){
            type = "A:";
         } else {
            type = "Q:";
         }    
         //print data of tree node
         output.println(type);
         output.println(current.data);
         //print left branch
         writeTree(current.left,output);
         //print right branch
         writeTree(current.right,output);   
      }
   } 
   
   //use current question tree to play the game
   public void play() {
      this.questionTree = play(this.questionTree);     
   }
   
   //ask questions by traversing through question tree, go to the left
   //branch if player inputs words starting with "y", otherwise go to 
   //right branch. If a leaf node is hit then make a guess and ask if
   //it's correct or not. 
   //pre: the tree includes at least one question in the root and two
   // answers as root's children 
   private QuestionNode play(QuestionNode current){
      Scanner console = new Scanner(System.in);
      if (current.left == null && current.right == null) {
         //base case, when leaf node reached
         System.out.println("I guess that your object is "
                            + current.data + "!");                    
         System.out.print("Am I right? (y/n)? ");
         if (console.nextLine().trim().toLowerCase().startsWith("y")){
            //print awesome message if win
            System.out.println("Awesome! I win!");
         } else {
            //ask player for help if lose
            System.out.println("Boo! I Lose.  Please help me get better!");
            System.out.print("What is your object? ");
            String ans = console.nextLine();
            System.out.println("Please give me a yes/no question that "
                               + "distinguishes between "
                               + ans + " and "+ current.data + ".");
            System.out.print("Q: ");
            String newQuestion = console.nextLine();
            System.out.print("Is the answer \"yes\" for " + ans + "? (y/n)? ");
            //update questionTree
            if(console.nextLine().trim().toLowerCase().startsWith("y")){
               current = new QuestionNode(newQuestion,new QuestionNode(ans)
                                          ,new QuestionNode(current.data));   
            } else {
               current = new QuestionNode(newQuestion,new QuestionNode(current.data)
                                          ,new QuestionNode(ans)); 
            } 
         }                                           
      } else {
         //recursive base
         System.out.print(current.data + " (y/n)? ");
         if (console.nextLine().trim().toLowerCase().startsWith("y")) {
            current.left = play(current.left);
         } else {
            current.right = play(current.right);     
         }
      }
      return current;
   }
   


}