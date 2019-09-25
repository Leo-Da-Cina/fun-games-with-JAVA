//author: Leo Zheng, drleozheng@gmail.com
//Dec 7th 2015

//Huffman node 
// a class representing a huffman node for a binary tree

public class HuffmanNode implements Comparable<HuffmanNode> {
   //field that stores ASCII number
   public final int data;
   //field that stores frequency 
   public final int freq;
   //fields that point to left and right childen
   public HuffmanNode left;
   public HuffmanNode right;
   
   //construct a leaf ascii node 
   public HuffmanNode(int data,int freq){
      this.data = data;
      this.freq = freq;
      this.left = null;
      this.right = null;
   }
   
   //construct a non-ascii node with branches
   public HuffmanNode(int freq,HuffmanNode left,HuffmanNode right){
      this.data = -1;//non ascii
      this.freq = freq;
      this.left = left;
      this.right = right;  
   }
   
   //comparable method
   public int compareTo(HuffmanNode other){
      return this.freq - other.freq;
   
   }
   
 

}
