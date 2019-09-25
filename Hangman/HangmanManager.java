//author: Leo Zheng, drleozheng@gmail.com
//Nov 20th 2015

//HangmanManager
/*This HangmanManager class is to work with HangmanMain to play hangman game.

Class briefing is as follows. 
1) Fields:
  currentWords is a TreeSet that includes the group of words, it's updated
  after each guess made by the user. 
  currentPattern is a char array which stores the current pattern associated
  with words in currentWords.
  guessesAvailable is number of guesses left for the user, it decreases each
  time the player made a wrong guess guessedLetter piles up all guesses made
  by the user in a set.
2) constructor: 
  it initializes the fields by using user-passed string list of words,the 
  length of words to be guessed and maximum number of wrong guesses allowed.
  Two private methods,initializeCurrentWords() and initializePattern are used
  to help accomplish intialization of currentWords and currentPattern.
  
3) Methods
   recod() is the most important method and decomposed into several private 
   methods such as updateCurrentWordsAndPattern(), mappingCurrentWords(),
   updateCurrentWords(),updateCurrentPatter(),getPattern(),countOccurrences() 
4) Other auxiliary methods:
   words(), guesses(),guessesLeft(),pattern() are for client user to see
   state of fields.    
*/


import java.util.*;

public class HangmanManager {
   //field
   private Set<String> currentWords;
   private int guessesAvailable;
   private Set<Character> guessedLetters;
   private char[] currentPattern;
   //constructor
   //pre: the passed List<String> contains non-empty lowercase strings
   public HangmanManager(List<String> dictionary, int length, int max){
      if (length < 1 || max < 0 ) {
         throw new IllegalArgumentException();
      }
      
     //initialize currentWords field
      currentWords = new TreeSet<String>();
      initializeCurrentWords(dictionary,length);
      //initialize other fields
      guessesAvailable = max;
      guessedLetters = new TreeSet<Character>();
      currentPattern = initializePattern(length);
   }
   
   //return a char array of dashes. the number of dashes
   //is given by the input integer length
   private char[] initializePattern(int length) {
      char[] pattern = new char[length];
      for (int i = 0 ; i < length; i++) {
         pattern[i] = '-';
      }
      return pattern;
   }
   
   // add the words of given length in the user-passed dictionary to 
   // field currentWords set
   // the user-passed dictionary should be a string list, the length of word
   // should be an integer
   private void initializeCurrentWords(List<String> dictionary, int length) {
      for(String str : dictionary) {
         if (str.length() == length ) {
            currentWords.add(str);
         }
      }
   }
   
   //update currentWords set after each guess made by the player
   //update currentPattern
   //returns number of occurrences of user guess (char type) in
   //the new pattern
   private int updateCurrentWordsAndPattern(char guess) {
      Map<String,Set<String>> allWords
          = new TreeMap<String,Set<String>>();
      int occOfGuess = 0;
      String newPattern = "";
      // go through currentWords set and find the pattern of each word
      // map each pattern and associate words set
      allWords = mappingCurrentWords(guess); 
      // update currentWords by choosing the largest group of words
      // and return the new pattern
      newPattern = updateCurrentWords(allWords);   
      //update currentPattern
      updateCurrentPattern(newPattern);
      // return the number of occurrences of guess in the pattern
      return occOfGuess = countOccurrence(newPattern,guess);
   }
   
   //make a map(key,value) for currentWords set, each key is one pattern 
   //determined by input char guess, each associated value is a tree set
   // that contains all words of that pattern
   private Map<String,Set<String>> mappingCurrentWords(char guess){
      Map<String,Set<String>> allWords
          = new TreeMap<String,Set<String>>();
      for (String str : currentWords ) {
         // get the pattern
         String pattern = getPattern(str,guess);
         //map the pattern and add the word to proper set
         Set<String> value = new TreeSet<String>();
         value.add(str);
         if(!allWords.containsKey(pattern)) {
            allWords.put(pattern,value);
         } else {
            allWords.get(pattern).add(str);
         }  
      } 
      return allWords;
   }
   
   // this method accepts a string word and a char letter
   // return the pattern of the word based on occurrence of the letter
   // e.g. "cook" , 'o', the pattern is "-oo-"
   private String getPattern(String word, char letter){
      String pattern = "";      
      for(int i = 0; i < word.length(); i++) {
         if (word.charAt(i) == letter) {
            pattern += letter;
         } else {
            pattern += '-';
         }
      }
      return pattern;  
   }
   
   // update currentWords and return new pattern associated with 
   // updated currentWords. This method takes a Map of all old Words
   // and choose the largest group of words as new currentWords set
   private String updateCurrentWords(Map<String,Set<String>> allWords){
      int cnt = 0;
      String pattern = "";
      for(String key : allWords.keySet()) { 
         if (cnt == 0) {
            currentWords = allWords.get(key);
            pattern = key; 
            cnt++;
         } else if (allWords.get(key).size() > currentWords.size()) {
            currentWords = allWords.get(key);
            pattern = key;
         }   
      }   
      return pattern;
   }

   //update currentPattern 
   //e.g.String pattern = "--o-", old value of currentPattern = "-c--"
   //then this method update currentPattern as "-co-"
   //pre:the position of letters in pattern and old value not overlap
   // and pattern and currentPattern of same length
   private void updateCurrentPattern(String pattern) {
      
      for(int i = 0; i < pattern.length();i++){
         if(pattern.charAt(i) != '-'){
            currentPattern[i] = pattern.charAt(i);
         }
      }
   }
   
   
   //return number of occurrences of given letter in the given string
   private int countOccurrence(String word, char letter) {
      int occurrences = 0;
      for(int i = 0; i < word.length(); i++){
         if (word.charAt(i) == letter) {
            occurrences++;
         }
      }
      return occurrences;
   }
   
   //access current set of words
   public Set<String> words() {
      return currentWords;
   }
   
   //neturn umber of guesses the player has left
   public int guessesLeft() {
      return guessesAvailable;
   }
   
   //return list of guessed letters
   public Set<Character> guesses() {
      return guessedLetters; 
   }
   
   //return current pattern to be displayed for the game 
   public String pattern() {
      if (currentWords.isEmpty()) {
         throw new IllegalStateException();
      } else {
      
         return new String(currentPattern).trim();
      }
   }
   
   //this method takes user input guess,decide what set of words to use 
   //,return number of occurrences of guessed letter in the new pattern, 
   // update the number of guesses left, add the guessed letter to the 
   // guessed list
   public int record(char guess) {
      if (guessesAvailable < 1 || currentWords.isEmpty()) {
         throw new IllegalStateException(); 
      }
         
      if (!currentWords.isEmpty() && guessedLetters.contains(guess)) {
         throw new IllegalArgumentException();
      }
      
      //add guess to the guessed letters set
      guessedLetters.add(guess);
      // update currentWords set and currentPattern
      int occOfGuess = updateCurrentWordsAndPattern(guess);
      // update the number of guesses left
      if (occOfGuess < 1 ) {
         guessesAvailable--;
      }
      // return number of occurrences of guess in the new pattern
      return occOfGuess; 
   }
   



}