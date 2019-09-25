// StarFig
// Ling 
// This program prints out StarFigures

public class StarFig {
   
   public static void main (String[] args){
      DubBars();
      Chair();
      System.out.println();
      DubBars();
      Chair();
      DubBars();
      System.out.println();
      Gravity();
      DubBars();
      Chair();
   }
   // print out double bars
   public static void DubBars() {
       System.out.println("*****");
       System.out.println("*****");
   }
   // print out a chair
   public static void Chair() {
       System.out.println(" * * ");
       System.out.println("  *  ");
       System.out.println(" * * ");
   }
   // print three vertical stars
   public static void Gravity() {
       System.out.println("  *  ");
       System.out.println("  *  ");
       System.out.println("  *  ");      
   }

}