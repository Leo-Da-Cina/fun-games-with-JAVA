//author: Leo Zheng, drleozheng@gmail.com
//Nov 13th 2015
//Grammar

/*This Grammar class is to work with GrammarMain to generate occurrences of 
given nonTerminal by randomly combine the terminals

Class briefing is as follows. 
1) Fields:
  a Map field, it stores user passed string list as a map by splitting
  nonterminals and rules when initialized.
2) constructor: 
  it initialize the field by using user passed string list and a private method
  named mappingList()
3) Method getRandom():
   It generates given number of occurrences of given nonTerminals
   its core is the private method randGenerateOccurrence()

4) Other auxiliary methods:
  isNonTerminal() tests if a symbol is a nonTermnal or terminal
  toString() returns a string of availible nonTerminals in alphabetical order    
*/

import java.util.*;

public class Grammar {
   //field
   private Map<String,List<String>> rulesMap; 

   //constructor
   public Grammar(List<String> rules) {
      if (rules.isEmpty() || rules == null) {
         throw new IllegalArgumentException();
      }
      //initialize field
      mappingList(rules);   
   
   }
   
   // this method takes a string list    
   // each element of the string list is splited into two parts
   // which are stored as (key, value) in TreeMap field. 
   private void mappingList(List<String> strList) {
      String[] pieces;
      List<String> valuePiece;
      rulesMap = new TreeMap<String,List<String>>();
      for(int i = 0; i < strList.size(); i++) {
            // each element of strList contains two parts seperated by "::="
            // those on the left of "::=" will be stored as keys
            // while those on the right as values
            pieces = strList.get(i).split("::=");
            //further split the right part into string array by delimiter "|"
            //and store them in a string list
            valuePiece = stringToList(pieces[1]);
            List<String> value = new ArrayList<String>(valuePiece);
           
            if (!rulesMap.containsKey(pieces[0])) {
               rulesMap.put(pieces[0],value);
            } else {
               //append the value to old value for the same key
               rulesMap.get(pieces[0]).addAll(value);
            }
      
      }     
       
   }
   
   // split given string by deilimter "|" 
   // remove the head and trailing spaces of each splitted sub strings
   // return a string list that stores the sub strings
   private List<String> stringToList(String str) {
      String[] pieces = str.split("\\|");
      for (int i = 0; i < pieces.length; i++) {
         pieces[i] = pieces[i].trim();
      }
      return Arrays.asList(pieces);
   }
   
   // given a string symbol, return true if it is non-terminal
   // otherwise return false
   public boolean isNonTerminal(String symbol) {
      //a symbol is a nonTerminal if it's in the key set of rulesMap
      //for (String key: rulesMap.keySet()){
        // if (symbol.equals(key)) {
          //  return true;
         //}  
      //}
      if (rulesMap.containsKey(symbol)) {
         return true;
      }  
       
      return false;
   } 

   //return all the non-terminals as a string in alphabetical order
   public String toString(){
      String allNonTerminals = "[";
      int cnt = 0;
      for (String key : rulesMap.keySet()) {    
         allNonTerminals += key;
         if (cnt < rulesMap.size()-1) {
            allNonTerminals += ",";
         }   
         cnt++;
      }
      return allNonTerminals += "]";
   }
   
   // return a String type occurrence of given nonTerminal by randomly
   // choosing a rule and symbols within chosen rule.  
   // pre: the input string is a nonTerminal
   private String randGenerateOccurrence(String nonTerminal){
      
      String occurrence = "";
      String chosenRule;
      Random rand = new Random();
      //fetch the rules of given nonTerminal from rulesMap
      List<String> rules = rulesMap.get(nonTerminal);
      // randomly choose one rule
      chosenRule = rules.get(rand.nextInt(rules.size()));
      // split the symbols of chosen rule by space delimiter 
      String[] symbols = chosenRule.split("\\s+");
      // get terminals combination by recursively calling the method
      for (int i = 0; i < symbols.length; i++ ) {
         if (!isNonTerminal(symbols[i])) {
            occurrence += symbols[i].trim() + " ";
            
         } else {
            occurrence += randGenerateOccurrence(symbols[i]);   
         }      
      }
   
      return occurrence;   
   }
    
   // given a nonTerminal(String type) and number (int type) this method
   // randomly produces number occurrences of that nonTerminal expressed
   // as a combination of terminals by following the rules set for that
   // nonTerminal.Each element of returned string list is one occurrence.   
   // pre: input number is not negative and input string is nonTerminal
   public List<String> getRandom(int number, String nonTerminal) {
      //throw exception if number of occurrences to be produced is negative
      if (number < 0) {
         throw new IllegalArgumentException();
      }
      // throw exception if input string is not a nonTerminal
      if ((!isNonTerminal(nonTerminal)) || nonTerminal == null){
         throw new IllegalArgumentException();
      }
      
      List<String> occurrenceList = new ArrayList<String>();
      for (int i = 0; i < number; i++) {
         String occurrence = randGenerateOccurrence(nonTerminal);
         occurrenceList.add(occurrence.trim());
      }
      
      return occurrenceList;
   }



}