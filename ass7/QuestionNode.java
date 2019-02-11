// Xin Lin
// 3/1/18
// CSE143
// TA:  Michael Gofman
// Assignment #7
//
// This class defines an object QuestionNode that stores a string and two
// references that point to the left and right node.
public class QuestionNode{
   public String data;
   public QuestionNode left;
   public QuestionNode right;
   
   // Post: Construct a QuestionNode with given data and it points to the given
   // left and right nodes.
   public QuestionNode(String data, QuestionNode left, QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right;
   }
   
   // Post: Constructs a QuestionNode with given data and it does not point to
   // any left or right nodes.
   public QuestionNode(String data){
      this(data, null, null);
   }
}