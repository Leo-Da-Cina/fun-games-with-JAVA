import java.util.*;
public class Test1 {
    public static void main(String[] args) {
       int[] num = {1,6,3,8,5,12,7,11,12,14,21};
       
       Queue<Integer> q1 = new LinkedList<Integer>(); 
       for (int n : num) {
         q1.add(n);
       } 
       System.out.println("Queue q1  = " + q1); 
       removeAll(q1,12);
       System.out.println("Queue q1  = " + q1);  
       Stack<Integer> s = new Stack<Integer>();
       queueToStack(q1,s); 
       System.out.println("Stack s = " + s);
        
    }
    
      
    public static void removeAll(Queue<Integer> q, int num) {    
      int oldsize = q.size();
      int n = 0;
      for (int i = 0; i < oldsize; i++) {
         n = q.remove();
         if (n != num) {
            q.add(n);
         }
      
      }      
    
    }
    
    public static void queueToStack(Queue<Integer> q, Stack<Integer> s) {
      while( !q.isEmpty()) {
         s.push(q.remove());    
     
      }   
    
    
    }
 
    
}