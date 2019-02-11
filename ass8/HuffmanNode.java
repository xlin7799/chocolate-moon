// Xin Lin
// 3/1/18
// CSE143
// TA:  Michael Gofman
// Assignment #7
//
// This class defines an object HuffmanNode that keeps track of the character
// value, frequency and two references that point to the left and right
// HuffmanNode.

public class HuffmanNode implements Comparable<HuffmanNode>{
   public int charValue;
   public int frequency;
   public HuffmanNode left;
   public HuffmanNode right;
   
   // Post: constructs a HuffmanNode with the given character value and
   // and frequency.
   public HuffmanNode(int charValue, int frequency){
      this.charValue = charValue;
      this.frequency = frequency;
   }
   
   // Post: constructs a HuffmanNode that points to the given left and right
   // node. Sets the frequency to the sum of the left and right nodes'
   // frequencies.
   public HuffmanNode(HuffmanNode left, HuffmanNode right){
      this.frequency = left.frequency + right.frequency;
      this.left = left;
      this.right = right;   
   }
   
   // Post: subtracts the given other node's frequency from this node's
   // frequency and returns the difference. Defines the node with the smaller
   // frequency to be the smaller node.
   public int compareTo(HuffmanNode other){
      return this.frequency - other.frequency;
   }
}