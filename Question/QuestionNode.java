//author: Leo Zheng, drleozheng@gmail.com
//Dec 2nd 2015


//make a Question node
public class QuestionNode {
   
   public final String data;
   public QuestionNode left;
   public QuestionNode right;
   
   //construct a leaf node
   public QuestionNode(String data){
      this(data,null,null);
   
   }
   //construct a node with left and right branch
   public QuestionNode(String data,QuestionNode left,QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right;
   
   }
   

}