//author: Leo Zheng, drleozheng@gmail.com
//Dec 7th 2015

//Huffman node 
//this class provides methods that can compress and decompress
//text files via Huffman encoding and decoding

import java.util.*;
import java.io.*;

public class HuffmanCode {
   
   private HuffmanNode root;
   //constructor1
   //initialize root by making a binary tree from a priority queue
   //that stores Huffman nodes which are made given frequencies
   public HuffmanCode(int[] frequencies){
      Queue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
      //make Huffman nodes using info in frequencies array and store
      //them in a priority queue, elements in frequencies set natural order
      for(int i = 0; i < frequencies.length; i++){
         if(frequencies[i] > 0){ 
            pq.add(new HuffmanNode(i,frequencies[i]));
         }
      }  
      //make a binary tree using Huffman nodes stored in Priority queue
      //lower frequency nodes are at deeper level of the tree
      while(pq.size() > 1){
         HuffmanNode leftNode = pq.poll();
         HuffmanNode rightNode = pq.poll();
         HuffmanNode parent = new HuffmanNode(leftNode.freq + rightNode.freq
                                              ,leftNode,rightNode);
         pq.add(parent);
      }
      root = pq.peek();
       
   }
   
   //constructor2, construct a binary tree from contents in input
   //each leaf node of the tree is Huffman node which contains the
   //ascii value of a character
   //pre: Scanner input is not empty
   public HuffmanCode(Scanner input){
      //assign arbitrary number to initialize root
      root = new HuffmanNode(0,null,null);     
      //make binary tree with Huffman nodes from .code file saved by save()
      while(input.hasNextLine()){
         int n = Integer.parseInt(input.nextLine());//ascii value
         String code = input.nextLine();//Huffman code for that character
         //make a huffman Node ,frequency field is arbitrarily set as 0
         HuffmanNode node = new HuffmanNode(n,0);
         //add node to the tree
         HuffmanNode current = root;
         root = makeTreeFromCode(code,node,current);
      }
   }
   
   //add HuffmanNode node to the tree whose root is HuffmanNode current
   //using information stored in String code.The return type of this method
   //is HuffmanNode so that tree can be updated by using x=change(x)
   private HuffmanNode makeTreeFromCode(String code,HuffmanNode node
                                        ,HuffmanNode current){
      if(code.length() == 1){
         //base case
         if(code.charAt(0) == '0'){
            current.left = node;// 0 for left branch
         } else {
            current.right = node;// 1 for right branch
         }   
      } else {
         //recursive case
         if(code.charAt(0) == '0'){
            if(current.left == null){
               current.left = new HuffmanNode(0,null,null);
            }
               current.left = makeTreeFromCode(code.substring(1),node
                                            ,current.left);  
         } else {
            if(current.right == null){
               current.right = new HuffmanNode(0,null,null);
            } 
            current.right = makeTreeFromCode(code.substring(1),node
                                             ,current.right);
         }
      }
      return current;
   }
   
   //save Huffman code of all characters on .code file via PrintStream output
   public void save(PrintStream output){
      String code = "";
      HuffmanNode now = root;
      saveCodeFile(now,output,code);   
   }
   
   //given root of a tree (HuffmanNode type) and PrintStream output,
   //traverse the tree preorder, print out ascii number of each leaf node
   //followed by huffman code in the output file
   //pre: all leaf nodes contain ascii numbers, all non-leaf nodes are non
   //ascii nodes and the tree is not empty
   private void saveCodeFile(HuffmanNode now,PrintStream output,String code){
      //base case, when hit a leaf node
      if (now.left == null && now.right == null){
         output.println(now.data);//print out ascii code
         //print out Huffman code
         output.println(code);
      } else {
         //recurisve case
         saveCodeFile(now.left,output,code + "0");
         saveCodeFile(now.right,output,code + "1");
      }   
   }
   
   //translate compressed file contained in input to decompressed
   //characters using coding information in the binary tree root
   //characters are saved in a txt file via PrintStream output
   public void translate(BitInputStream input, PrintStream output){
      int bit;
      HuffmanNode current = root;
      while(input.hasNextBit()){ 
          //output the character if a leaf node hit otherwise go to next bit
         if(current.left == null && current.right == null){
            output.write(current.data);
            current = root;
         }        
         bit = input.nextBit();
         if(bit == 0) {
            current = current.left;
         } else {
            current = current.right;
         }
  
      }
      //output the last character
      output.write(current.data);
   }

}



