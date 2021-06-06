import java.util.Scanner;

/* 
 * File: BST.java
 * Author: Paul Ippolito
 * Course: CMPT 435L
 * Version 1.0
 * 
 * This file contains the BSTNode class and its methods as well as
 * EncryptionTree class and its methods.
 * 
 * 
 * A BSTNode represents a node in a binary search tree. Each BSTNode object
 * stores a single item (called "data"). Each object also has left and right
 * pointers, which point to the left and right subtrees.
 *
 * The BST can be seen as superclass of the BSTNode class, so that the BST 
 * may make changes to the internals of a BSTNode.
 *
 * The constructor is provided for you; read it carefully.
 *
 * The getLeft(), getRight(), and getData() methods are useful for the
 * EncryptionTree class (or any class that wants to have read-only access to the
 * nodes of a BST).
 *
 * The printPreorder() traverses this node and its children recursively in
 * pre-order and prints each node it visits to standard output (i.e.
 * System.in). It presumes that "data" can be printed; that is, 
 * "System.out.print(this.data)" is a statement that makes sense. At each 
 * level of the tree it adds two spaces of indentation to show the structure 
 * of the tree. The run-time of printPreorder() is O(n). Can you figure out 
 * why?  Could it be made more efficient?
 *
 * The minNode() and maxNode() methods are useful in verifySearchOrder(). They
 * should find the leftmost and rightmost node at or below the node they are
 * called on. These can be implemented recursively or iteratively.
 *
 * The function verifySearchOrder() can be used to do verifications of the
 * binary search tree's order. It can and should be used for testing purposes.
 * If you implement minNode() and maxNode() efficiently, the run-time of
 * verifySearchOrder() is O(n^2) for this (potentially unbalanced) tree. Can you
 * figure out why?  Could it be made more efficient using different techniques?
 *
 * No one may call the copy constructor on a BSTNode, it is hereby forbidden,
 * so it is protected and will crash the program if called.
 */

class BSTNode {
  
  protected  BSTNode(BSTNode t) { assert(false); }
  protected  String  data;
  protected  BSTNode left;
  protected  BSTNode right;
  
  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; left = l; right = r;
  }
  
   public BSTNode getLeft()  { return left;  }
   public BSTNode getRight() { return right; }
   public String  getData()  { return data;  }
  
   /*
    * method takes in a String called indent. Looks through the nodes, adds
    * spaces, and nulls as needed for given input. It also prints out our
    * current string of data of input.
    */
  public void printPreorder(String indent) {
    
    System.out.println(indent + this.data);
  
    indent += "  ";
  
    // if our left node is null, return indent and NULL
    if(this.left == null) 
      System.out.println(indent + "NULL");
    
  else 
      this.left.printPreorder(indent);
    
  
    // return indent and null if right node is null
    if(this.right == null) {
      System.out.println(indent + "NULL");
      return;
    } 
    
  else 
      this.right.printPreorder(indent);
    
  }
  
  /*
   * minNode gets the pointer's left node as long as it has nodes on the left
   * as soon as it is null, return the pointer it got.
   */
  public BSTNode minNode() {
    
    BSTNode pointer = this;
  
    while(pointer.getLeft() != null) {
    
      pointer = pointer.left;
    
    }
    return pointer;
  }
  
  /*
   * This method is exactly like minNode, but for nodes on the right of the
   * root or other nodes. Checks for nodes and sets a pointer to right until
   * null, then returns the complete pointer
   */
  public BSTNode maxNode() {
    
    BSTNode pointer = this;
  
    while(pointer.getRight() != null){
    
      pointer = pointer.right;
    }
    return pointer;
  }
  
  /* professor's implementation of verifySearchOrder() */
  public void verifySearchOrder() {
    if(left != null) {
    
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    
    }
    
    if(right != null) {
    
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }
}

/* A BST is a String-based class, but could easily be coded as a generic-type 
 * type class (e.g. with T), that represents a binary search tree. It has one
 * data member, "root", which is a pointer to the root of the tree.
 *
 * The constructor is provided for you.
 *
 * The insert() method places the given item in the tree, unless the item is
 * already in the tree. The insertion should follow the algorithm we discuss in
 * class.
 *
 * The remove() method removes the given item from the tree, if it is in the
 * tree. The remove should follow the algorithm we discuss in class.
 *
 * The printPreorder() and verifySearchOrder() methods simply calls the relevant
 * per-node methods on the root.
 *
 * No one may call the copy constructor on a BST, it is hereby forbidden, so
 * it is protected and will crash the program if called.
 */

class BST {
  protected BST(BST t)         { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }
  protected BSTNode root;
  
  public BST() {
    this.root = null;
  }
  
  public void insert(String item) {
    
    BSTNode node = new BSTNode(item,null,null);
  
    if(this.root == null) 
      this.root = node;
     
  else {
      BSTNode current = this.root;
    
      while(true) {
        int dir = item.compareTo(current.data);
    
        if(dir == 0) 
          return;
        
    
        if(dir < 0) {
          if(current.left == null) {
            current.left = node;
            return;
          }
      
          current = current.left;
        } 
    
    else {
          if(current.right == null) {
            current.right = node;
            return;
          }
          current = current.right;
        }
      }
    }
  }
  
  /*
   * Used to remove the given item from our tree if it exists in our tree
   * creates BSTNode parent and tRemove. parent set to the current root,
   * toremove set to null. boolean found initialized to false
   */
  public void remove(String item) {
    
    if(this.root == null) 
      return;
    
    BSTNode parent   = this.root;
    BSTNode toRemove = null;
    boolean found    = false;
    int childCount   = 0;
    
    // if parents data is our item, we've found our item to be removed
    if(parent.data.equals(item)) {
      toRemove = this.root;
      found = true;
    } 
    
  else {
    
      while(parent!=null) {
      
        if(parent.left != null && parent.left.data.equals(item)) {
          toRemove = parent.left;
          found = true;
          break;
        }
    
        if(parent.right != null && parent.right.data.equals(item)) {
          toRemove = parent.right;
          found = true;
          break;
        }
    
        int dir = item.compareTo(parent.data);
        
        if(dir < 0) 
          parent = parent.left;
        
    
    else {
      
          parent = parent.right;
        }
      }
    }
  
    // if we find our item to remove, check if it has any child nodes
    if(found) {
      
      if(toRemove.left != null) 
        childCount++;
      
    
      if(toRemove.right != null) 
        childCount++;
      
    
      // massive switch statement based on if there are any children
      switch(childCount) {
      
      // if our parent node is still our root and is the one being removed,
      // set both of them to null
        case 0:
          if(parent == toRemove && parent == this.root) {
            
            parent = null;
            this.root = null;
      
          } 
      
          // if toRemove is left child of root, set it to null
      else if(toRemove == parent.left) {
        
            parent.left = null;
          
      } 
      
          // same principle as left
      else {
            parent.right = null;
          }
      
          toRemove = null;
          break;
      
          // case for children of children nodes
        case 1:
          BSTNode grandchild;
          
          // if there's a child on the toremove's left, set it to null
          if(toRemove.left != null) {
            grandchild = toRemove.left;
            toRemove.left = null;
          } 
      
      else {
            grandchild = toRemove.right;
            toRemove.right = null;
          }
      
          // if current root is to be removed, set the grandchild to root
          if(parent == toRemove && parent == this.root) {
            this.root = grandchild;
            parent = null;
          } 
      
      // if parents left is to be removed, change it to the grandchild
      else if(parent.left == toRemove) {
            parent.left = grandchild;
          } 
      
      else {
            parent.right = grandchild;
          }
      
          toRemove = null;
          break;
      
          // begin checking leftmost nodes and parents
        case 2:
          BSTNode leftmost = toRemove.right;
          BSTNode leftmostParent = toRemove;
          
          if(leftmost.left != null){
        
            // if leftmost still has nodes to the left, set the leftmostParent
            // to leftmost, then stop once the leftmost only has null left 
            // nodes
            
            while(leftmost.left != null){
              leftmostParent = leftmost;
              leftmost = leftmost.left;
            }
      
            leftmostParent.left = leftmost.right;
            leftmost.right = toRemove.right;
          }
      
          leftmost.left = toRemove.left;
          
          // if parent is one being removed and is root, set to leftmost
          if(parent == toRemove && parent == this.root) {
            this.root = leftmost;
            parent = null;
          } 
      
      else if(parent.left == toRemove){
            parent.left = leftmost; //set parents left to leftmost node
          } 
      
      else {
            parent.right = leftmost;
          }
      
          toRemove.left  = null;
          toRemove.right = null;
          toRemove = null;
          break;
      
        default: break;
      }
    }
  }
  
  public void printPreorder() { if (root != null) root.printPreorder("");}
  
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder();}
}


/* An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word) 
 * it leads to.
 *
 * The constructor method is provided for you.
 *
 * The encrypt() method takes a String object and returns a string of the form 
 * "rX" where "r" is a literal letter r, and X is a sequence of 0 and 1 
 * characters (which may be empty). The r stands for "root", and each 0 and 1 
 * represent the left/right path from the root to the given object, with 0 
 * indicating a left-branch and 1 indicating a right-branch. If the object is 
 * not in the dictionary, an empty string (or the string "?") should be 
 * returned.
 *
 * The decrypt() method takes an encrypted string (or path through the tree) in
 * the form provided by encrypt(). It should return a pointer to the associated
 * string for the given path (or NULL if the path is invalid).
 */

class EncryptionTree extends BST {
  
  public EncryptionTree() {}
  public String encrypt(String item) {
    
    // if the root is null, return our non-dictionary character
    if(this.root == null) 
      return "?";
    
  
    BSTNode current = this.root;
    String  path    = "r";
    String  word;
    String temp[] = item.split("\'");
    
    // if string is length 1, set it to first index of our temp array,
    // otherwise, set it to the second index
    if(temp.length == 1) {
    
      word = temp[0];
    } 
  
  else {
      word = temp[1];
    }
  
    while(true) {
      int dir = word.trim().compareTo(current.data);
    
      if(dir == 0) {
        return path;
      }
    
      // if both nodes on left and right are null, return ?
      if(current.left == null && current.right == null) 
        return "?";
      
    
      // dir less than our root and its left node isn't null, go left
      if(dir < 0 && current.left != null) {
        current = current.left;
        path += "0";
      } 
    
      // if the opposite is true, go right and append 1 to path
    else if(dir > 0 && current.right != null) {
        current = current.right;
        path += "1";
      } 
      
    else {
        return "?";
      }
    }
  }
  
  /*
   * Method public String decrypt(String path)
   * 
   * Takes the path String as a parameter, creates two arrays directions[] and
   * temp[], where temp splits path. It then goes through the list of
   * directions, decrypting the encrpyted string from encrypt()
   * 
   */
  
  public String decrypt(String path) {
    
    if(this.root == null) {
      return "?";
    }
  
    String directions[];
    BSTNode current = null;
    String temp[] = path.split("\'");
  
    if(temp.length == 1) 
      directions = temp[0].split("");
     
  
  else 
      directions = temp[1].split("");
    
  
    // for the entire lenght of diretions list, look for r, 0, or 1 at current
    // index
    
    for(int i=0; i<directions.length; i++) {
      
      if(directions[i].equals("r"))
        current = this.root; //set BSTNode current to the current nodes root
       
    // if direction is 0, go left
    else if(directions[i].equals("0")) 
        current = current.left;
      
    // if directions is 1, go right
    else if(directions[i].equals("1"))
        current = current.right;
      
    
      // if the node is still null at the end, return "bad" character
      if(current == null) 
        return "?";
      
    }
  
    return current.data;
  }
}