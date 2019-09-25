//Ling Zheng
//Oct 23rd, 2015
//CSE 143x
//HW #4 Personality
/*Given a file that includes the person names and their answers 
  to the personality test, analyze each person's personality 
  attributes and output the results to a new file */
  
import java.io.*;
import java.util.*;  
  
public class Personality {
   //define a class constant to specify the dimensions of the test
   public static final int N_DIMENSION = 4;

   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      // get input file name from user via console
      Scanner input = getInputFile(console);
      // get output file name from user via console 
      PrintStream output = getOutputFile(console);
      
      while(input.hasNextLine()) {
         // read that person'sname
         String name = input.nextLine();
         // read that person's answers as a string
         String ans = input.nextLine();
         // calculate the percentages of "B"s in each dimension
         int[] percent_b = percentB(ans);
         //assign the personality type based on percentage of "B"s
         char[] personality = pTypeAssign(percent_b); 
         //output the results
         output.print(name+": "+Arrays.toString(percent_b)+" = ");
         output.print(personality);
         output.println();
      
      }
   
   }
   // method that gets input file name from user
   public static Scanner getInputFile(Scanner console) 
          throws FileNotFoundException{
      System.out.print("input file name ? ");
      File f = new File(console.nextLine());
      while(!f.canRead()) {
         System.out.println("The file does exist! Try a new name please.");
         System.out.print("input file name ?");
         f = new File(console.nextLine());
      }
      return new Scanner(f);
   }
   
   // method that gets output file name from user
   public static PrintStream getOutputFile(Scanner console)
          throws FileNotFoundException {
      System.out.print("output file name ?");
      File f = new File(console.nextLine());
      return new PrintStream(f);
   
   }
   
   // method that accepts one person's answers as a string
   // and then output the percentages of "B"s for each dimension
   public static int[] percentB(String ans){
      ans = ans.toLowerCase();
      int[] percent_b = new int[N_DIMENSION];
      double[] a_cnt = new double[N_DIMENSION*2-1];
      double[] b_cnt = new double[N_DIMENSION*2-1];
      
      for(int i = 0; i< ans.length(); i++) {
         if (ans.charAt(i) == 'a') {
            a_cnt[i % 7]++;
         }
         if (ans.charAt(i) == 'b') {
            b_cnt[i % 7]++;
         }
      
      }
      percent_b[0] = (int)(Math.round(100*b_cnt[0]/(b_cnt[0]+a_cnt[0])));  
      for(int i = 1; i < N_DIMENSION; i++) {
         percent_b[i] = (int)(Math.round(100*(b_cnt[2*i]+b_cnt[2*i-1])
                      / (b_cnt[2*i]+a_cnt[2*i]+b_cnt[2*i-1]+a_cnt[2*i-1]))); 
      
      }
      
      return percent_b;
   
   }
   
   //Assign the personaltity type for each dimension based on 
   // the percentage of "B"s
   // e.g. percent_b = [30 70 40 10], personality type will be 
   //  ENTJ
   public static char[] pTypeAssign(int[] percent_b) {
      char[] typea = {'E','S','T','J'};
      char[] typeb = {'I','N','F','P'};
      char[] personaltype = new char[N_DIMENSION];
      for(int i = 0; i < N_DIMENSION; i++) {
         if (percent_b[i] < 50 ) {
            personaltype[i] = typea[i];}
         else{
            if (percent_b[i] > 50) {
               personaltype[i] = typeb[i];}
            else {
               personaltype[i] = 'X';}
         }
      }
      
      return personaltype;   
   }

}  
