/**
 * File: AVLTree.java
 * Author: Paul Ippolito
 * Course: CMPT 435
 * Version: 1.0
 * 
 * This file contains the declaration and associated functions of the 
 * AVLNode Object, the declaration of the AVLTree Class, 
 * and the Declaration as well as Associated functions
 * of the EncryptionTree
 */

/* An AVLNode represents a node in an AVL-balanced binary search tree. Each
 * AVLNode object stores a single item (called "data"). Each object also has
 * left and right references, which point to the left and right subtrees, and it
 * knows its own height (the path length to its deepest descendant).
 *
 * The AVLTree can be seen as superclass of the AVLNode class, so that the 
 * AVLTree may make changes to the internals of an AVLNode.
 *
 * Many of the methods in this class are virtually identical to those in the
 * BSTNode in the previous project (#3), including the constructor,
 * getLeft(), getRight(), getData(), printPreorder(), verifySearchOrder(),
 * minNode(), maxNode(), and the copy constructor.
 *
 * The function verifyBalance() can be used to do verifications that the AVL
 * balance property holds at each node. It also can and should be used for
 * testing purposes. What is its running time?
 *
 * The singleRotateLeft() and singleRotateRight() methods do a single rotation
 * on the node they are called on, and return a reference to the node that takes
 * its place (so that the node's parent's reference can be changed).  Note that
 * these methods should update the heights of some nodes as necessary.
 *
 * The doubleRotateLeftRight() and doubleRotateRightLeft() methods do a double
 * rotation on the node they are called on. This is really simple if you have
 * implemented the single rotation methods; my double rotation methods are two
 * lines each. These methods return a reference to the node which took the place
 * of the node the method was called on (so that the node's parent's reference 
 * can be changed).
 *
 * The getHeight() method is a static method which takes a reference to a node,
 * and returns the height of that node (or -1 if the reference is NULL). This
 * makes it easy to find the height of any node with a reference, without having
 * to check for NULL.
 *
 * The updateHeight() method calculates and updates the value of the height on
 * the node it's called on. It assumes that the height values for the two
 * children of this node are correct, and uses them.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * AVLNode
 * 
 * This class implements all the required functions and variables for the nodes
 * within an AVL tree
 */

class AVLNode {
  AVLNode(AVLNode t) {
    assert (false);
  }

  AVLNode(String d, AVLNode l, AVLNode r, int h) {
    data = d;
    left = l;
    right = r;
    height = h;
  }

  protected String data;
  protected AVLNode left, right;
  protected int height;

  /**
   * singleRotateLeft
   * 
   * Rotates the given node to the left and updates the heights of the nodes
   * 
   * Takes in the node we want to rotate, firstNode, 
   * and returns the updated heights and children if applicable
   * 
   */

  protected AVLNode singleRotateLeft(AVLNode firstNode) {
    AVLNode secondNode = firstNode.right;
    AVLNode Temp = secondNode.left;
    secondNode.left = firstNode;
    firstNode.right = Temp;
    
    firstNode.height = 
        max(getHeight(firstNode.left), getHeight(firstNode.right)) + 1;
    
    secondNode.height = 
        max(getHeight(secondNode.left), getHeight(secondNode.right)) + 1;

    return secondNode;

  }

  /**
   * singleRotateRight
   * 
   * Rotates the given node to the right and updates the heights of the node
   * 
   * Takes in secondNode, our desired node to rotate, and returns the updated
   * heights of the nodes and their children if they have any
   * 
   */
  protected AVLNode singleRotateRight(AVLNode secondNode) {
    AVLNode firstNode = secondNode.left;
    AVLNode Temp = firstNode.right;
    firstNode.right = secondNode;
    secondNode.left = Temp;
    
    secondNode.height = 
        max(getHeight(secondNode.left), getHeight(secondNode.right)) + 1;
    
    firstNode.height = 
        max(getHeight(firstNode.left), getHeight(firstNode.right)) + 1;

    return firstNode;
  }

  /**
   * doubleRotateLeftRight
   * 
   * Rotates the given node to the left, then to the right and updates the
   * heights
   * 
   * Parameters: thirdNode: which is to be rotated
   * 
   * Return: thirdNode with updated height and children (if any)
   * 
   */
  protected AVLNode doubleRotateLeftRight(AVLNode thirdNode) {

    thirdNode.left = singleRotateLeft(thirdNode.left);
    return singleRotateRight(thirdNode);
  }

  /**
   * doubleRotateRightLeft
   * 
   * Rotates the given node to the right, then to the left and updates the
   * heights
   * 
   * Parameters: fourthNode, which we'll rotate
   * 
   * Return: fourthNode with its updated height and children (if any)
   * 
   */
  protected AVLNode doubleRotateRightLeft(AVLNode fourthNode) {

    fourthNode.right = singleRotateRight(fourthNode.right);
    return singleRotateLeft(fourthNode);
  }

  protected static int getHeight(AVLNode n) {
    return n != null ? n.height : -1;
  }

  protected void updateHeight() {
    System.out.println("hit here");
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;

  }

  int max(int a, int b) {
    return (a > b) ? a : b;
  }

  /**
   * getLeft
   * 
   * This returns the left branch of the called BSTNode
   * 
   * Return value: the left node of the current BSTNode
   */
  public AVLNode getLeft() {
    return left;
  }

  /**
   * getRight
   * 
   * This returns the right branch of the called BSTNode
   * 
   * Return value: the right node of the current BSTNode
   */
  public AVLNode getRight() {
    return right;
  }

  /**
   * getData
   * 
   * This returns the raw string data of the current BSTNode
   * 
   * Return value: a string containing the actual data for the BSTNode
   */
  public String getData() {
    return data;
  }

  /**
   * printPreorder
   * 
   * traverses the node and prints out each of its children recursively and in
   * pre-order. It then adds spaces to make it easier to visualize the tree
   * 
   */
  public void printPreorder(String indent) {

    System.out.println(indent + this.data);

    indent += "  ";

    if (this.left == null) {
      System.out.println(indent + "NULL");
    }

    else {
      this.left.printPreorder(indent);
    }

    if (this.right == null) {
      System.out.println(indent + "NULL");
      return;
    }

    else {
      this.right.printPreorder(indent);
    }
  }

  /* professor's implementation of verifySearchOrder(); don't change it */
  public void verifySearchOrder() {
    if (left != null) {
      assert (left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert (data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }

  /* professor's implementation of verifyBalance(); don't change it */
  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert (heightDiff <= 1);
    if (left != null)
      left.verifyBalance();
    if (right != null)
      right.verifyBalance();
  }

  /**
   * minNode
   * 
   * Looks at the child node left of the current node
   * 
   * Return Value: the left child node of the current node
   * 
   */
  public AVLNode minNode() {
    AVLNode nodeToExamine = this;
    while (nodeToExamine.getLeft() != null) {
      nodeToExamine = nodeToExamine.left;
    }
    return nodeToExamine;
  }

  /**
   * maxNode
   * 
   * Looks at the child node right of the current node
   * 
   * Return Value: the right child node of the current node
   * 
   */
  public AVLNode maxNode() {
    AVLNode nodeToExamine = this;
    while (nodeToExamine.getRight() != null) {
      nodeToExamine = nodeToExamine.right;
    }
    return nodeToExamine;
  }
}

/**
 * BST
 * 
 * This class implements all the required functions and variables in order to
 * use an AVL tree
 */

/* An AVLTree is a String-based class that represents an AVL-balanced binary 
 * search tree. It has one data member, "root", which is a reference to the 
 * root of the tree.
 *
 * Many of the methods in this class are virtually identical to methods in the
 * BST from the previous project (#3), including the constructor,
 * printPreorder(), verifySearchOrder(), and copy constructor.
 *
 * The insert() and remove() methods behave as in the plain BST, but both
 * methods should rebalance the tree as necessary. This is best done by creating
 * an array of references to AVLNode objects as the insert/remove methods search
 * for the place to do their work.  This array of references represents the path
 * taken to get from the root to the place where a change occurs in the tree.
 * Note that for remove(), this path might go deeper than the node removed, in
 * the case of removing a node with two children (think carefully about this).
 * After insert/remove finish updating the tree, they can pass the path to
 * rebalancePathToRoot() which actually does the rebalancing. Think about how
 * large the array of references needs to be, at its largest. An AVL tree with
 * height 30 must have at least 3,524,577 nodes, and if it has height 50, it
 * must have at least 53,316,291,172 nodes -- probably more than we care to put
 * in the tree. These results come from the minimum size of an AVL tree of
 * height h, which is described in your book as: S(h) = S(h - 1) + S(h - 2) + 1
 * (and base cases S(0) = 1, S(1) = 2).
 *
 * The printLevelOrder() method prints out all the nodes in the tree in
 * level-order (root, then the root's children, then their children, etc.). This
 * is like performing a breadth-first search of the tree. The method should put
 * up to 20 nodes on each line, and use multiple lines as necessary. This method
 * should use a Java queue, and it is iterative (not recursive). This method is
 * useful if we want to transmit the information for building exactly the same
 * tree to our correspondent. If we were to take all the non-NULL nodes and
 * insert them in the order printed by this method, we would get the exact same
 * tree. We would not always be able to construct the exact same tree if we were
 * to use printPreorder() instead.
 *
 * The rebalancePathToRoot() method takes an array of references to AVLNode
 * objects, and the number of references that are on the array. This array should
 * represent the path that needs rebalancing after an insert or remove. It's
 * probably best to have the root at the start of the array. This method should
 * walk from the bottom of the path to the root, checking for imbalances, and
 * correcting any it finds by calling rotation methods as necessary to correct
 * imbalances.
 */
class AVLTree {
  protected AVLNode root;

  AVLTree(AVLTree t) {
    assert (false);
  }

  /**
   * AVLTree
   * 
   * Creates a new AVL Tree with a null root
   * 
   */
  AVLTree() {
    root = null;
  }

  /**
   * rebalancePathToRoot
   * 
   * Rebalances the tree by reading in the specified node and running the
   * required rotate command if it finds an imbalance
   * 
   * Parameters: node: the node we check to see if our tree has any imbalances
   * 
   * Return: the node with its height, data and children (rotated if necessary)
   */

  protected AVLNode rebalancePathToRoot(AVLNode node) {
    // if the node is null return it exactly
    if (node == null) {
      return node;
    }
    // if the left height is greater than the right by more than 1
    if (height(node.left) - height(node.right) > 1) {
      // if the left node's left node is greater than or equal to the
      // left node's right node do a single right rotate
      if (height(node.left.left) >= height(node.left.right)) {
        node = node.singleRotateRight(node);
      }
      // otherwise, do a left right rotate
      else {
        node = node.doubleRotateLeftRight(node);
      }
    }
    // if the right height is greater than the left by more than 1
    else if (height(node.right) - height(node.left) > 1) {
      
      // if the right node's right node is greater than or equal to the
      // right node's left node do a single left rotate
      if (height(node.right.right) >= height(node.right.left)) {
        node = node.singleRotateLeft(node);
      }
      
      // otherwise, do a right-left rotate
      else {
        node = node.doubleRotateRightLeft(node);
      }
    }
    
    // update the height
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    
    // return our node
    return node;
  }

  /**
   * insert
   * 
   * Places a new AVL Node into our tree unless the item is already 
   * in our tree. It then rebalances the tree if necessary.
   * 
   * Parameters: node: the node where we want to check data: the data value of
   * our node
   * 
   * Return: the node with its data, height, and children (if applicable)
   * 
   */
  public AVLNode insert(AVLNode node, String data) {
    
    // if the node is null create a new node
    if (node == null) {
      return (new AVLNode(data, null, null, 0));
    }
    
    // if the data entered is less than the data of the current node
    if (data.compareTo(node.data) < 0) {
      
      // move it left
      node.left = insert(node.left, data);
    }
    
    // if the data entered is greater than the data of the current node
    else if (data.compareTo(node.data) > 0) {
      
      // move it right
      node.right = insert(node.right, data);
    }
    
    // rebalance the tree and return it
    return rebalancePathToRoot(node);
  }

  /**
   * remove
   * 
   * Finds then removes the specified node from our AVL tree. The tree is then
   * rebalanced if necessary.
   * 
   * Parameters: root: the node where we want to check (starting at the root)
   * value: the data value of our node
   * 
   * Return: the rebalanced node with its data, height, and children 
   * (if needed)
   * 
   */
  public AVLNode remove(AVLNode root, String value) {
    
    // if the root is null, just return it;
    if (root == null)
      return root;

    // if the data entered is less than the data of the current node
    if (value.compareTo(root.data) < 0) {
      
      // move left
      root.left = remove(root.left, value);
    }
    
    // if the data entered is greater than the data of the current node
    else if (value.compareTo(root.data) > 0) {
      
      // move right
      root.right = remove(root.right, value);
    }
    
    // if the left node isn't null and the right node isn't null
    else if (root.left != null && root.right != null) {
      
      // set the data to the right node data
      
      root.data = minValueNode(root.right).data;
      
      // set the right node data to the removal return;
      root.right = remove(root.right, root.data);
    }
    
    // otherwise
    else {
      
      // if the left node isn't null, set the root to the left node,
      // otherwise set it to the right
      root = (root.left != null) ? root.left : root.right;
    }
    
    // re-balance the tree
    return rebalancePathToRoot(root);

  }

  /**
   * printLevelOrder
   * 
   * Prints out our tree in a level order fashion, adding nulls where there are
   * no leaves on a node.
   * 
   */
  public void printLevelOrder() {
    
    // create a queue for our nodes
    Queue<AVLNode> qnode = new LinkedList<AVLNode>();
    
    // create a queue for our Node's data
    Queue<String> qdata = new LinkedList<String>();
    
    int lineCount = 0;
    int levelNodes = 0;
    
    // if the tree is empty, just print NULL
    if (root == null) {
      System.out.println("NULL");
      return;
    }

    // add the root to the node queue
    qnode.add(root);
    
    // add the root data to the data queue
    qdata.add(root.data);
    
    // while our node queue isn't empty
    while (!qnode.isEmpty()) {
      
      // level is equal to the size of the queue
      levelNodes = qnode.size();
      
      // while the level is greater than 0
      while (levelNodes > 0) {
        
        // created a node from the front of the queue, then remove it from
        // the queue
        AVLNode tempData = (AVLNode) qnode.remove();

        // if our node's left data is't null add it to both queues
        if (tempData.left != null) {
          qnode.add(tempData.left);
          qdata.add(tempData.left.data);

        }
        
        // if it is null, add "NULL" to only the data queue
        if (tempData.left == null) {

          qdata.add("NULL");

        }
        
        // if our node's right data is't null add it to both queues
        if (tempData.right != null) {
          qnode.add(tempData.right);
          qdata.add(tempData.right.data);

        }
        
        // if it is null, add "NULL" to only the data queue
        if (tempData.right == null) {

          qdata.add("NULL");
        }
        
        // reduce the level by 1
        levelNodes--;
      }

    }

    // while our data queue isn't empty
    while (!qdata.isEmpty()) {

      // get the data from the front of the queue
      String dataVal = qdata.remove();
      
      // print out that data value
      System.out.print(dataVal + " ");
      
      // if the amount printed on a line is 20
      if (lineCount >= 19) {
        
        // reset the lineCount
        lineCount = 0;
        
        // go to the next line
        System.out.println("");
      }
      
      // otherwise increase the line count
      else {
        lineCount++;
      }

    }
    
    // go to the next line when completed printing
    System.out.println("");
  }

  /**
   * printPreorder
   * 
   * if the root isn't null, calls printPreorder to print out the nodes
   * 
   */
  public void printPreorder() {
    if (root != null) {
      root.printPreorder("");

    }
  }

  /**
   * verifySearchOrder
   * 
   * if the root isn't null, calls verifySearchOrder
   * 
   */
  public void verifySearchOrder() {
    if (root != null) {
      root.verifySearchOrder();
    }
  }

  /**
   * verifyBalance
   * 
   * checks if the root isn't null, then recursively calls verifyBalance 
   * 
   */

  public void verifyBalance() {
    if (root != null) {
      root.verifyBalance();
    }
  }

  /**
   * height
   * 
   * gets the height of the specified node
   * 
   * Parameters: Node: the node we want to check
   * 
   * Return: -1 if the node is null, the height of the node if it is not
   * 
   */
  int height(AVLNode Node) {

    return Node == null ? -1 : Node.height;
  }

  /**
   * minValueNode
   * 
   * gets the smallest value in the tree
   * 
   * Take in Node and return the leftmost, which should be the smallest
   * 
   */
  AVLNode minValueNode(AVLNode node) {
    AVLNode current = node;

    while (current.left != null)
      current = current.left;

    return current;
  }

}

/*
 * The EncryptionTree for this project is exactly the same as for the previous
 * project, except that it now has an AVLTree as its parent class.
 */
class EncryptionTree extends AVLTree {
  public EncryptionTree() {
  }

  /**
   * Encrypt
   * 
   * Takes a String object and encrypts it
   * 
   * Parameters: item: a string of the data to find in our tree to encrypt
   * 
   * Return Value: a string with the encrypted value
   * 
   */
  public String encrypt(String item) {
    
    // if our root is null return a "?"
    if (this.root == null) {
      return "?";
    }
    
    // Create a new node at the root
    AVLNode currentNode = this.root;
    
    // set the current encrypted path to "r" (for "Root")
    String path = "r";
    
    // Create the word String
    String word;
    
    // Create a string list with the item starting at the text: '
    String encryptionList[] = item.split("\'");
    
    // if the list length is 1, set the only item in it to word
    if (encryptionList.length == 1) {
      word = encryptionList[0];
    }
    
    // otherwise set the word to the second item in the list
    else {
      word = encryptionList[1];
    }
    
    // while true
    while (true) {
      
      // compare our word to the current node's data
      int compare = word.trim().compareTo(currentNode.data);
      
      // if it is equal return our path as it is
      if (compare == 0) {
        return path;
      }
      
      // if both branches are null, return a "?"
      if (currentNode.left == null && currentNode.right == null) {
        return "?";
      }
      
      // if it is smaller and the left branch is not null
      if (compare < 0 && currentNode.left != null) {
       
        currentNode = currentNode.left;
        
        // add a 0 to the path
        path += "0";
      }
      
      // if it is larger and the right branch is not null
      else if (compare > 0 && currentNode.right != null) {
        
       
        currentNode = currentNode.right;
        
        path += "1";
      }
      
      // otherwise return a "?"
      else {
        return "?";
      }
    }
  }

  /**
   * decrypt
   * 
   * Takes a String object and decrypts it to plainText
   * 
   * Parameters: item: a string of the data to find in our tree to decrypt
   * 
   * Return Value: the node with the plainText Data
   * 
   */
  public String decrypt(String path) {
    
    // if the root is null return a "?"
    if (this.root == null) {
      return "?";
    }
    
    // Make a list of nodes
    String nodeList[];
    
    // Made a new CurrentNode assigned to null
    AVLNode currentNode = null;
    
    // Create a string list with the item starting at the text: '
    String decryptList[] = path.split("\'");
    
    // if the length of the list is 1, set the decryptList at 0 to the 
    // node list splitting it up
    if (decryptList.length == 1) {
      nodeList = decryptList[0].split("");
    }
    
    // otherwise set the decrypt list at 1 to node list and split it up by
    // character
    else {
      nodeList = decryptList[1].split("");
    }
    
    // iterate though our list
    for (int i = 0; i < nodeList.length; i++) {
      
      // if the current text = r, set the current node to the root
      if (nodeList[i].equals("r")) {
        currentNode = this.root;
      }
      
      // if the current text = 0, set the current node to the left branch
      else if (nodeList[i].equals("0")) {
        currentNode = currentNode.left;
      }
      
      // if the current text = 1, set the current node to the right branch
      else if (nodeList[i].equals("1")) {
        currentNode = currentNode.right;
      }
      
      // if the current node is null, return "?"
      if (currentNode == null) {
        return "?";
      }
    }
    
    // return the node's value
    return currentNode.data;
  }
}