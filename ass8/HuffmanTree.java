// Xin Lin
// 3/1/18
// CSE143
// TA:  Michael Gofman
// Assignment #7
//
// This class defines an object HuffmanTree that stores a binary tree with each
// of its leaf node representing a character. The HuffmanTree gives each of the
// leaf node an unique Huffman code by assigning a 0 to each of the left node
// and 1 to each of the right node starting from the overall root. It contains
// a method that decodes a given encoded input file.

import java.util.*;
import java.io.*;

public class HuffmanTree{
   private HuffmanNode overallNode;
   
   // Post: constructs a HuffmanTree using the frequency of each character
   // given.
   public HuffmanTree(int[] count){
      Queue<HuffmanNode> subtrees = new PriorityQueue<HuffmanNode>();
      for(int i = 0; i < count.length; i++){
         if(count[i] != 0){
            subtrees.add(new HuffmanNode(i, count[i]));
         }
      }
      subtrees.add(new HuffmanNode(count.length, 1));
      while(subtrees.size() > 1){
         subtrees.add(new HuffmanNode(subtrees.remove(), subtrees.remove()));
      }
      overallNode = subtrees.remove();
   }
   
   // Pre: the input must contains a HuffmanTree written in standard formart.
   //
   // Post: reconstructs a HuffmanTree using the input file given.
   public HuffmanTree(Scanner input){
      int charValue = Integer.parseInt(input.nextLine());
      String code = input.nextLine();
      overallNode = read(input, "", charValue, code);
   }
   
   // Post: according to the input file, constructs a subtree that has its  
   // left-most leaf node with the character value and code given. Returns 
   // the overall node of the subtree.
   private HuffmanNode read(Scanner input, String currentCode, int charValue,
                                  String code){
      if(currentCode.equals(code)){ 
         return new HuffmanNode(charValue, 0);
      }else{
         HuffmanNode leftNode = read(input, currentCode + "0", charValue, code);
         charValue = Integer.parseInt(input.nextLine());
         code = input.nextLine();
         HuffmanNode rightNode = read(input, currentCode + "1", charValue, code);
         return new HuffmanNode(leftNode, rightNode);
      }
   }
   
   // Post: writes the HuffmanTree in standard format.
   public void write(PrintStream output){
      write(overallNode, output, "");
   }
   
   // Post: writes the subtree with the given current root node in standard
   // format.
   private void write(HuffmanNode current, PrintStream output, String code){
      if(current.left == null){
         output.println(current.charValue);
         output.println(code);
      }else{
         write(current.left, output, code + 0);
         write(current.right, output, code + 1);
      }
   }
   
   // Pre: the input file must contains a legal encoding of characters for
   // this tree's Huffman code.
   //
   // Post: decodes the given encoded input file using the HuffmanTree and 
   // writes the original file into the output file given. Stops decoding
   // when it reaches a character with value equals to the eof given.
   // Character with the eof value should not be written into the output file.
   public void decode(BitInputStream input, PrintStream output, int eof){
      int charValue = eof + 1;
      while(charValue != eof){
         HuffmanNode current = overallNode;
         while(current.left != null){
            if(input.readBit() == 0){
               current = current.left;
            }else{
               current = current.right;
            }
         }
         charValue = current.charValue;
         if(charValue != eof){
            output.write(charValue);
         }
      }
   }
}