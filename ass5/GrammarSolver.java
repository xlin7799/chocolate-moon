// Xin Lin
// 2/7/18
// CSE143
// TA:  Michael Gofman
// Assignment #5
//
// This class defines an object GrammarSolver that manipulates the grammar
// rules given. It contains a method that generates random examples of a given
// nonterminal for given number of times by picking a rule in each nonterminal
// randomly until the terminals are picked.

import java.util.*;

public class GrammarSolver{

   // Stores the grammar rules for each nonterminal
   private SortedMap<String, String[]>grammarMap;
   
   // Pre: the grammar passed in must not be empty and there must be exacly one
   // entry for each nonterminal.(throws IllegalArgumentException otherwise)
   // 
   // Post: constructs a GrammarSolver object. Stores the grammar rules given
   // for each nonterminal.
   public GrammarSolver(List<String>grammar){
      if(grammar.isEmpty()){
         throw new IllegalArgumentException();
      }
      grammarMap = new TreeMap<String, String[]>();
      for(String line : grammar){
         String[] parts = line.split("::=");
         String nonterminal = parts[0];
         String[] rules = parts[1].split("[|]");
         if(grammarMap.get(nonterminal)!=null){
            throw new IllegalArgumentException();
         }
         grammarMap.put(nonterminal,rules);
      }
   }
   
   // Post: returns whether the grammar rules contain the given symbol as a 
   // nonterminal.
   public boolean grammarContains(String symbol){
      return grammarMap.keySet().contains(symbol);
   }
   
   // Pre: the grammar rules must contain the given symbol as a nonterminal and
   // the times given must be at least 0(throws IllegalArgumentException
   // otherwise)
   //
   // Post: generates the given symbol randomly for given number of times. The 
   // rules for each nonterminal are chosen randomly with equal probability.
   // The symbol passed in is case sensitive.
   public String[] generate(String symbol, int times){
      if(!grammarContains(symbol)||times < 0){
         throw new IllegalArgumentException();
      }
      String[] phrases = new String[times];
      for(int i = 0; i < times; i++){
         phrases[i] = generate(symbol);
      }
      return phrases;
   }
   
   // Post: generates and returns a given symbol randomly for only one time.
   // Tokens in the string are seperated by one space and the string does not
   // have leading or trailing spaces.
   private String generate(String symbol){
      if(!grammarMap.keySet().contains(symbol)){
         return symbol;
      }
      String[] rules = grammarMap.get(symbol);
      Random random = new Random();
      String[] itsRule = rules[random.nextInt(rules.length)].trim().
                           split("[ \t]+");
      String onePhrase = "";
      for(int i = 0; i < itsRule.length; i++){
         onePhrase = onePhrase + " " + generate(itsRule[i]);
      }
      return onePhrase.trim();
   }
   
   // Post: returns the String representation of the nonterminals in the
   // grammar rules. The symbols are in sorted order, seperated by comma and
   // enclosed in square brackets.
   public String getSymbols(){
      return grammarMap.keySet().toString();
   }
}