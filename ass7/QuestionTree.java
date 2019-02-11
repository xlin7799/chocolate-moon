// Xin Lin
// 3/1/18
// CSE143
// TA:  Michael Gofman
// Assignment #7
//
// This class defines an object QuestionTree that stores a binary tree. It 
// contains a method that asks a series of questions to guess an object 
// and expand the tree when it guesses the object wrong.

import java.util.*;
import java.io.*;

public class QuestionTree{
   private Scanner console;
   private QuestionNode overallRoot;
   
   // Post: constructs a QuestionTree that has one leaf node storing "computer"
   // as its data.
   public QuestionTree(){
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");
   }
   
   // Pre: the input file msut be legal and of standard format.
   //
   // Post: replace the current tree with a new tree read from the given file.
   public void read(Scanner input){
      overallRoot = readTree(input);
   }
   
   // Post: read the tree from the input file given and returns the root
   // node of the tree.
   private QuestionNode readTree(Scanner input){
      String nodeType = input.nextLine();
      String data = input.nextLine();
      if(nodeType.equals("A:")){
         return new QuestionNode(data);
      }else{
         QuestionNode left = readTree(input);
         QuestionNode right = readTree(input);
         return new QuestionNode(data, left, right);
      }
   }
   
   // Post: writes the question tree into the output file given in standard
   // format.
   public void write(PrintStream output){
      write(overallRoot, output);
   }
   
   // Post: writes the subtree with the given root node into the given output
   // file.
   private void write(QuestionNode root, PrintStream output){
      if(root.left == null){
         output.println("A:");
         output.println(root.data);
      }else{
         output.println("Q:");
         output.println(root.data);
         write(root.left, output);
         write(root.right, output);
      }
   }
   
   // Post: Using current QuestionTree, asks the user a series of yes/no 
   // questions until it is able to guess the object. If guesses the object
   // wrong, expands the tree by including the user's object and adding a new
   // question that could distinguish the user's object and the wrong object
   // guessed.
   public void askQuestions(){
      if(overallRoot.left == null){
         lastQuestion(null, overallRoot);
      }else{
         askQuestions(overallRoot);
      }
   }
   
   // Post: Use the current subtree with root node given to ask the user
   // questions until the object can be guessed.
   private void askQuestions(QuestionNode root){
      QuestionNode nextNode;
      if(yesTo(root.data)){
         nextNode = root.left;
      }else{
         nextNode = root.right;
      }
      if(nextNode.left == null){
         lastQuestion(root, nextNode);
      }else{
         askQuestions(nextNode);
      }
   }
   
   // Post: guesses the object that the user is thinking about. If guessed the
   // object wrong, expands the tree by including the user's object and adding
   // a new question that could distinguish the wrong object guessed and the
   // user's object.
   private void lastQuestion(QuestionNode previous, QuestionNode current){
      if(yesTo("Would your object happen to be " + current.data + "?")){
         System.out.println("Great, I got it right!");
      }else{ 
         System.out.print("What is the name of your object? ");
         String answer = console.nextLine();
         System.out.println("Please give me a yes/no question that"); 
         System.out.println("distinguishes between your object");
         System.out.print("and mine--> "); 
         String question = console.nextLine();
         QuestionNode newQuestion;
         QuestionNode newLeaf = new QuestionNode(answer);
         if(yesTo("And what is the answer for your object?")){
            newQuestion = new QuestionNode(question, newLeaf, current);
         }else{
            newQuestion = new QuestionNode(question, current, newLeaf);
         }
         if(previous == null){
            overallRoot = newQuestion;
         }else{
            if(previous.left.data.equals(current.data)){
               previous.left = newQuestion;
            }else{
               previous.right = newQuestion;
            }
         }
      }
   }
   
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}