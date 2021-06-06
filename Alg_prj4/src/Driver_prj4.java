/**
   * File: Driver_prj4.java
   * Author: Paul Ippolito
   * Course: CMPT 435
   * Version: 1.0
   * 
   * This file contains the logic driver for the AVL Tree Program and processes
   * how the program converts input text into commands and data
   */

import java.util.Scanner;

public class Driver_prj4 {
  public static void main(String[] args) {
    
    // Create a Scanner to read in inputs
    Scanner input = new Scanner(System.in);
    
    // Create a new Encryption Tree
    EncryptionTree encrypTree = new EncryptionTree();
    
    // create a boolean to indicate completion 
    boolean done = false;
    
    // while we aren't done, done is false
    String nextLine;
    while (!done) {
      
      // Get the next line
      nextLine = input.nextLine();
      
      // create a new array and fill it with the command and associated data
      String lineToArray[] = nextLine.split(" ");
      
      // get the command from the front of the array
      char cmd = ' ';
      
      // if there's no command, continue on
      if (lineToArray[0].equals("")) {
        continue;
      }
      
      // otherwise get the letter command from the array to read as command
      else {
        cmd = lineToArray[0].charAt(0);
      }
      
      // if the command is q, end the program,
      if (cmd == 'q') {
        done = true;
        input.close();
      }
      
      // if the command is p, preorder the data in the tree and execute 
      // printPreorder
      
      else if (cmd == 'p') {
        if (encrypTree.root != null) {
          encrypTree.root.printPreorder("");
        }
      }
      
      // if the command is l, print the level order of the tree
      else if (cmd == 'l') {
        encrypTree.printLevelOrder();
      }
      
      // if the command is i, insert the data from the array into the
      // tree
      else if (cmd == 'i') {

        encrypTree.root = encrypTree.insert(encrypTree.root,
            lineToArray[1]);
      }
      
      // if command is r, remove the data from the array
      else if (cmd == 'r') {
        encrypTree.root = encrypTree.remove(encrypTree.root,
            lineToArray[1]);
      }
      
      // if command is e, encrypt the tree
      else if (cmd == 'e') {
        for (int i = 1; i < lineToArray.length - 1; i++) {
          System.out.print(encrypTree.encrypt(lineToArray[i]) + " ");
        }
        
        System.out.print(
            encrypTree.encrypt(lineToArray[lineToArray.length - 1]));
        System.out.println("");
      }
      
      // if command is d, decrypt the tree into plainText
      else if (cmd == 'd') {
        for (int i = 1; i < lineToArray.length - 1; i++) {
          System.out.print(encrypTree.decrypt(lineToArray[i]) + " ");
        }
        
        System.out.print(
            
            encrypTree.decrypt(lineToArray[lineToArray.length - 1]));
        System.out.println("");
      }
      

    }

  }
}