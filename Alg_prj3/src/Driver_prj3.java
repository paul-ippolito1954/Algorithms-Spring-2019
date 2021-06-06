import java.util.Scanner;

/*
 * File: Driver_prj3.java
 * Author: Paul Ippolito
 * Course: CMPT 435L
 * Version 1.0
 * 
 * This file contains the Driver for Project 3: Tree based Encryption and
 * Decryption. It creates a binary search tree to act as the encrpt/decrypt
 * cipher. Based on inputs given, it will read certain characters and correlate
 * to certain methods within BSTNode.java
 */

public class Driver_prj3 {


  public static void main(String[] args) {
    
    // create new EncryptionTree from BSTNode.java
    EncryptionTree encryptTree = new EncryptionTree();
    
    
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    
    boolean endInput= false;
  
    
    // has not reached final input from user
    while(endInput==false) {
      
      String line = input.nextLine();
      String lineArray[] = line.split(" ");
      
      /*switch statement correlated to different commands
        p will call printPreorder, i calls insert, r calls remove, e encrypts
        d decrypts, q quits. This fixes and error where input originally would
        not close. Each function can be found in BSTNode.java
      */
     
      switch(lineArray[0].charAt(0)) {
      
        case 'p':
          // if it reads p and root isn't null, printPreorder, else break.
          if(encryptTree.root != null)
            encryptTree.root.printPreorder("");
          
          break;
        
          // reads in i, inserts item into lineArray index 1 
        case 'i':
          
          encryptTree.insert(lineArray[1]);
          break;
        
          // reads r, removes item at lineArray index 1
        case 'r':
          encryptTree.remove(lineArray[1]);
          break;
 
          
        // begins encrypting given string
        case 'e':
          
          for(int i=1; i<lineArray.length-1; i++) {
            System.out.print(encryptTree.encrypt(lineArray[i]) + " ");
          }
          
          System.out.print(encryptTree.encrypt(lineArray[lineArray.length-1]));
          System.out.println("");
          break;
        
          // decrypts encrypted string 
        case 'd':
          for(int i=1; i<lineArray.length-1; i++) {
            System.out.print(encryptTree.decrypt(lineArray[i]) + " ");
          }
          
          System.out.print(encryptTree.decrypt(lineArray[lineArray.length-1]));
          System.out.println("");
          break;
        
          // closes input and gets read as final input. fixes data overflow
        case 'q':
          endInput = true;
          break;
      }
    }
  }
}
