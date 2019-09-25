import java.util.*;
public class Test {
    public static void main(String[] args) {
       Stack<Integer> s1 = {1,6,3,8,5,12};
       Stack<Integer> s2 = {7,2,1,0,11,10}; 
       boolean result;
       result = sameParityPattern(s1,s2);
       System.out.println(result); 
        
    }
    
    public static boolean 
               sameParityPattern(Stack<Integer> s1,Stack<Integer> s2){
    
    if (s1.size() != s2.size()) {
      return false;
    } else {
      boolean same = true;
      Stack<Integer> s3 = new Stack<Integer>();    
      // loop through both stacks,check the parity and
      // store the popped elements in a auxiliary stack
      while(same && !s1.isEmpty()) {
        int num1 = s1.pop();
        int num2 = s2.pop();
        if (num1 % 2 != num2 % 2) {
            same = false;
        }
        s3.push(num1);
        s3.push(num2);
        
      }
      // return the values back to the s1,s2 from s3
      while(!s3.isEmpty()){
         s2.push(s3.pop());
         s1.push(s3.pop());
      
      }
      
      // return test result
      return same;
    
    
    }
    
    
    
    }
    
}