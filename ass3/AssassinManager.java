// Xin Lin
// 1/11/18
// CSE143
// TA:  Michael Gofman
// Assignment #3
//
// This class defines an object AssassinManager that keeps track of a kill ring
// and a list of dead people who are already killed. This class contains
// a method that removes a person from the kill ring and adds him to
// the list of dead people.

import java.util.*;

public class AssassinManager{
   private AssassinNode alive;
   private AssassinNode dead;
   
   // Pre: the list names must not be empty. (throws IllegalArgumentException
   // otherwise)
   //
   // Post: constructs an AssassinManager object that keeps track of a kill
   // ring with names appearing in the same order as they appear in the
   // names given.
   public AssassinManager(List<String> names){
      if(names.size() == 0){
         throw new IllegalArgumentException();
      }
      int length = names.size();
      alive = new AssassinNode(names.get(length-1));
      for(int i = 0; i < length - 1; i++){
         alive = new AssassinNode(names.get(length-2-i), alive);
      }
   }
   
   // Post: prints the current kill ring
   public void printKillRing(){
      AssassinNode current = alive;
      while(current.next != null){
         System.out.println("    " + current.name + " is stalking " + 
                  current.next.name);
         current = current.next;
      }
      System.out.println("    " + current.name + " is stalking " + 
                  alive.name);
   }
   
   // Post: prints the names of people who are killed and their killers.
   // The most recently killed person is printed first.
   public void printGraveyard(){
      AssassinNode current = dead;
      while(current != null){
         System.out.println("    " + current.name + " was killed by " + 
                  current.killer);
         current = current.next;
      }  
   }
   
   // Post: returns whether the current kill ring contains the given name
   // ignoring the case of the letters
   public boolean killRingContains(String name){
      return contains(alive, name);
   }
   
   // Post: returns whether the graveyard contains the given name ignoring
   // the case of the letters. 
   public boolean graveyardContains(String name){
      return contains(dead, name);
   }
   
   // Post: returns whether the game is over(if there is only one person in
   // the kill ring)
   public boolean gameOver(){
      return alive.next == null;
   }
   
   // Post: returns the name of the winner and returns null if the game is not
   // over
   public String winner(){
      if(gameOver()){
         return alive.name;
      }else{
         return null;
      }
   }
   
   // Pre: the name given must be in the kill ring and the game must not
   // be over (throws IllegalArgumentException otherwise)
   //
   // Post: transfers the person with the given name to the graveyard from
   // the kill ring. The order of the rest of the people in the kill ring is
   // unchanged and the kill ring is relinked by skipping the person who is
   // killed. Ignores case when comparing names.
   public void kill(String name){
      if(!killRingContains(name)){
         throw new IllegalArgumentException();
      }
      if(gameOver()){
         throw new IllegalStateException();
      }
      AssassinNode prev = alive;
      if(prev.name.toLowerCase().equals(name.toLowerCase())){
         AssassinNode current = alive;
         while(current.next != null){
            current = current.next;
         }
         prev.killer = current.name;
         alive = alive.next;
         prev.next = dead;
         dead = prev;
      }else{
         while(!prev.next.name.toLowerCase().equals(name.toLowerCase())){
            prev = prev.next;
         }
         AssassinNode victim = prev.next;
         victim.killer = prev.name;
         prev.next = victim.next; 
         victim.next = dead;
         dead = victim;
      }
   }
   
   // Post: returns whether a given list node contains the name given. Ignores
   // case when comparing names.
   private boolean contains(AssassinNode front, String name){
      AssassinNode current = front;
      while(current != null){
         if(current.name.toLowerCase().equals(name.toLowerCase())){
            return true;
         }
         current = current.next;
      }
      return false;
   }
}