// Xin Lin
// 2/15/18
// CSE143
// TA:  Michael Gofman
// Assignment #4 bonus
//
// This class defines an object HangmanManager2 that extends the HangmanManager
// class. The record method lets the player lose instantly if possible when the
// player only has one guess left. The words and guesses methods return
// unmodifiable version of the sets to protect the private fields.

import java.util.*;

public class HangmanManager2 extends HangmanManager{
   
   private Set<String> wordsWrapper;
   private SortedSet<Character> guessesWrapper;
   
   public HangmanManager2(Collection<String> dictionary, int length, int max){
      super(dictionary, length, max);   
   }
   
   // Post: returns the unmodifibale version of the word set considered. The
   // wrapper object is only newly created when the word set considered is
   // changed.
   public Set<String> words(){
      if(wordsWrapper == super.words()){
         return wordsWrapper;
      }else{
         wordsWrapper = Collections.unmodifiableSet(super.words());
         return wordsWrapper;
      } 
   }
   
   // Post: returns the unmodifibale version of the set of characters guessed. 
   // The wrapper object is only newly created when the set of characters 
   // guessed is changed.
   public SortedSet<Character> guesses(){
      if(guessesWrapper == super.guesses()){
         return guessesWrapper;
      }else{
         guessesWrapper = Collections.unmodifiableSortedSet(super.guesses());
         return guessesWrapper;
      }
   }
   
   // Post: when the player only has one guess left, the record method picks
   // the first word in the word set that does not contain the character  
   // guessed to let the player lose instantly if such word exists. The super
   // class version of record is called if such word doesn't exist or the
   // player has more than 1 guesses left.
   public int record(char guess){
      if(super.guessesLeft() != 1){
         return super.record(guess);
      }else{
         for(String word : super.words()){
            if(word.indexOf(guess) == -1){
               super.words().clear();
               super.words().add(word);
               return super.record(guess);
            }
         }
         return super.record(guess);
      }
   }
}