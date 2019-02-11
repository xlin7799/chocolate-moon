// Xin Lin
// 2/1/18
// CSE143
// TA:  Michael Gofman
// Assignment #4
//
// This class defines an object HangmanManager that keeps track of the set of
// words considered, the current pattern of the words, the characters already
// guessed and the number of guesses left. It also contains method that records
// each guess and updates the word set and word pattern accordingly.

import java.util.*;

public class HangmanManager{
   
   // The set of words considered by Hangman
   private Set<String> considered;
   
   // The set of characters already guessed
   private SortedSet<Character> guesses;
   private String pattern;
   private int guessesLeft;
   
   // Pre: the length of word must be at least 1 and the max must not be
   // negative. (throws IllegalArgumentException otherwise)
   // 
   // Post: Constructs a HangmanManager object. Initializes the word set with
   // words of given length from the dictionary given, initializes the word
   // pattern with dashes representing unkown character and records the number
   // of wrong guesses allowed.
   public HangmanManager(Collection<String> dictionary, int length, int max){
      if(length < 1 || max < 0){
         throw new IllegalArgumentException();
      }
      considered = new TreeSet<String>();
      guesses = new TreeSet<Character>();
      guessesLeft = max;
      pattern = "";
      for(int i = 0; i < length - 1; i++){
         pattern += "- ";
      }
      pattern += "-";
      for(String word : dictionary){
         if(word.length() == length){
            considered.add(word);
         }
      }
   }
   
   // Post: returns the set of words considered.
   public Set<String> words(){
      return considered;
   }
   
   // Post: returns the number of wrong guesses left allowed to make.
   public int guessesLeft(){
      return guessesLeft;
   }
   
   // Post: returns the current set of characters already guessed. The 
   // characters are in sorted order.
   public SortedSet<Character> guesses(){
      return guesses;
   }
   
   // Pre: the set of words considered must not be empty.(throws
   // IllegalStateException otherwise)
   //
   // Post: returns the current pattern of words considered taking into account
   // the guesses already made. Letters that have never been guessed are
   // represented as dashes.
   public String pattern(){
      if(considered.isEmpty()){
         throw new IllegalStateException();
      }
      return pattern;
   }
   
   // Pre: the number of guesses left must be at least 1 and the word set
   // considered must not be empty.(throws IllegalStateException otherwise)
   // the character guessed must not be guessed before.(throws
   // IllegalArgumentException otherwise)
   // 
   // Post: Updates the set of characters guessed, updates the number of
   // guesses left, chooses the largest set of words with the same new pattern
   // to move forward, updates the pattern with the new pattern and returns the
   // number of occurences of the guessed letter in the new pattern. If there
   // are sets with the same largest number of words, then the set found first
   // is picked.
   public int record(char guess){
      if(guessesLeft < 1 || considered.isEmpty()){
         throw new IllegalStateException();
      }
      if(guesses.contains(guess)){
         throw new IllegalArgumentException();
      }
      guesses.add(guess);
      updatePattern(guess);
      int numOfOccurence = 0;
      for(int i = 0; i < pattern.length(); i++){
         if(pattern.charAt(i) == guess){
            numOfOccurence++;
         }
      }
      if(numOfOccurence == 0){
         guessesLeft--;
      }
      return numOfOccurence;
   }
   
   // Post: groups words considered with the same new pattern into sets and
   // chooses the largest set to move forward. Updates the pattern with the
   // new pattern. 
   private void updatePattern(char guess){
      Map<String, Set<String>> families = new TreeMap<String, Set<String>>();
      for(String word : considered){
         String itsPattern = pattern;
         for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == guess){
               itsPattern = itsPattern.substring(0, 2*i) + guess 
                              + itsPattern.substring(2*i + 1);
            }
         }
         if(families.get(itsPattern) == null){
            families.put(itsPattern, new TreeSet<String>());
         }
         families.get(itsPattern).add(word);
      }
      largestFamily(families);
   }
   
   // Post: Picks the largest set of words with the same new pattern to move
   // forward and updates the pattern with the new pattern.
   private void largestFamily(Map<String, Set<String>> families){
      int maxNum = 0; 
      for(String newPattern : families.keySet()){
         if(families.get(newPattern).size() > maxNum){
            pattern = newPattern;
            maxNum = families.get(newPattern).size();
         }
      }
      considered = families.get(pattern);
   }
}