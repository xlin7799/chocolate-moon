// Xin Lin
// 2/7/18
// CSE143
// TA:  Michael Gofman
// Assignment #5
//
// This class defines an object AnagramSolver that uses a given dictionary to
// find all possible words combinations that use the same set of letters as a
// given phrase.

import java.util.*;

public class AnagramSolver{
   private List<String> dictionary;
   
   // A map that connects each word with its letter count stored in a
   // LetterInventory object
   private Map<String, LetterInventory>dictionaryMap;
   
   // Post: constructs an AnagramSolver object that stores the words in the
   // given list as its dictionary. Counts the number of each letter in any
   // given word in the dictionary.
   public AnagramSolver(List<String> list){
      dictionary = list;
      dictionaryMap = new HashMap<String, LetterInventory>();
      for(String word: list){
         dictionaryMap.put(word, new LetterInventory(word));
      }
   }
   
   // Pre: the max must not be smaller than 0.(throws IllegalArgumentException
   // otherwise)
   //
   // Post: finds and prints all combinations of words that contain exactly the
   // same letters as the given phrase and have at most max number of words. If
   // max = 0, each combination can have unlimited number of words. Creates a
   // smaller dictionary that contains relevant words of a given string.
   public void print(String s, int max){
      if(max < 0){
         throw new IllegalArgumentException();
      }
      LetterInventory letters = new LetterInventory(s);
      List<String> dictionary2 = new ArrayList<String>();
      Stack<String> combination =  new Stack<String>();
      for(String word : dictionary){
         if(letters.subtract(dictionaryMap.get(word))!= null){
            dictionary2.add(word);
         }
      }
      explore(letters, combination, max, dictionary2);
   }
   
   // Post: finds and prints all combinations of words in the given dictionary
   // that use all letters in the letters object. The number of words in each 
   // combination is at most max (or unlimited when max =0).
   private void explore(LetterInventory letters, Stack<String> combination,
                                 int max, List<String> dictionary2){
      if(letters.isEmpty()){
         if(letters.isEmpty()){
            System.out.println(combination);
         }
      }else if(combination.size() < max || max == 0){
         for(String word : dictionary2){
            LetterInventory subtract = letters.subtract(
                                                dictionaryMap.get(word));
            if(subtract != null){
               combination.push(word);
               explore(subtract, combination, max, dictionary2);
               combination.pop();
            }
         }
      }
   }
}