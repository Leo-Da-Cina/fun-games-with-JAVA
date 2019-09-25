// author: Leo Zheng, drleozheng@gmail.com
// Nov 30th 2015

// this class is to work with AnagramMain.java to print all combinations of 
// words in the dictionary that are anagrams of given phrase

import java.util.*;

public class Anagrams{
   //fields
   private List<String> dictionary;
   private Map<String,LetterInventory> lettersOfDict;
   
   //constructor
   //pre: passed string list contains nonempty collection of nonempty letters
   // with no duplicates
   public Anagrams(List<String> dictionary) {
      //initialize fields
      this.dictionary = dictionary;  
      lettersOfDict = new HashMap<String,LetterInventory>();
      // map each word in the dictionary to a LetterInventory object
      for (String word : dictionary) {
         if (!lettersOfDict.containsKey(word)) {
            lettersOfDict.put(word,new LetterInventory(word));
         }
      } 
   
   }
   
   //print all combinations of words in the dictionary that have same letters
   //as the given string. This method also accepts an integer argument that
   //specifies maximum number of words in the combination. if the integer is 
   //zero , it means unlimited number of words. An IllegalArgumentException 
   // will be thrown if a negative integer is passed
   public void print(String s, int max) {
      // throw exception for illegal argument if negative max passed
      if (max < 0 ) {
         throw new IllegalArgumentException();
      }
      // a string list that stores answers
      List<String> ans = new ArrayList<String>();
      // construct a LetterInventory object for input String s
      LetterInventory lettersOfS = new LetterInventory(s); 
      List<String> relevantWords = new ArrayList<String>();
      Map<String,LetterInventory> relevantLetters 
                                 = new HashMap<String,LetterInventory>();
      //loop through dictionary to find relevant words                         
      for(String word : dictionary) {
         if (lettersOfS.subtract(lettersOfDict.get(word)) != null) {
            //store relevant word in the new smaller dictionary
            relevantWords.add(word);
            //store word and its LetterInventory in the new smaller map
            relevantLetters.put(word,lettersOfDict.get(word));
         }
      }
      //print anagrams of input String s 
      printAnagrams(relevantWords,relevantLetters,lettersOfS,max,ans);   
   }
 
   //this private method prints anagrams. It recursively
   //find combinations of words in the passed string list words. a hash map
   //that stores the words and the corresponding LetterInventory objects is
   //passed. int max indicates maximum number of words in the combination.
   //the solution will be stored in the List String ans. 
   //pre:lettersOfS is not null. max is non-negative, ans is empty at the very
   // beginning. 
   private void printAnagrams (List<String> words,                        
                     Map<String,LetterInventory> lettersOfWords,
                     LetterInventory lettersOfS,int max,List<String> ans){
   //base case, if lettersOfS is empty, a solution was found                  
   if (lettersOfS.isEmpty()) {
      if (max == 0 || ans.size() <= max) { 
         System.out.println(ans);
      } 
   } else {
      //recursive case
      //loop through all choices
      for(String word : words) {
         //add one word to the end of list
         ans.add(word);
         // recursively search the next level
         if (lettersOfS.subtract(lettersOfWords.get(word)) != null) {
            printAnagrams(words,lettersOfWords,
                        lettersOfS.subtract(lettersOfWords.get(word)),max,ans);
         }
         //remove the added word from list if it's not part of solution
         ans.remove(ans.size()-1);
      }
   
   }                
                     
   }
   


}