/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: Paul Ippolito
 * Due 1/30/2019
 * Version 1.0
 *
 * The program accepts a series of strings which correspond to commands.
 * It reads the first 4 characters of the given string, and edits the stack
 * accordingly. It traces the stack, analyzing for either valid or invalid
 * traces and outputs accordingly with the type of error, be it not all
 * functions returning, one returning instead of another, returning from one
 * that was never called, or a valid trace with its maximum depth
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  /*
   * main parameters: args -- the array of command line argument values return
   * value: nothing
   * 
   * the function reads in a text file line by line, analyzing the first 4
   * characters, checking for calls or returns. it also checks the stack for any
   * errors, and if there are any, said errors are displayed and the program
   * terminates. Checks for valid or invalid traces, checking and editing the
   * stack.
   */
  public static void main(String[] args) {
    // Here we initialize the scanner variable to read lines of input

    Scanner input = new Scanner(System.in);
    // Scanner input = new Scanner(System.in);
    String line;

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while
    // condition. If System.in has reached the end of the file, it will return
    // false and the loop will exit. Otherwise, it will return true and the
    // loop will continue.

    int lineNumber = 0;
    int maximum_depth = 0;
    int curr_depth = 0;
    boolean stopProgram = false;

    while (input.hasNext()) {
      line = input.nextLine();
      lineNumber++;

      String cmdString = line.substring(0, 4); // checks first 4 char of string
      String func; // variable for caught string to be pushed, pop, etc.,

      if (cmdString.equals("call")) { //checks if command is a call
        
        curr_depth++;
        if (curr_depth > maximum_depth) {
          maximum_depth = curr_depth;
        }
        func = line.substring(5);
        callStack.push(func);
      }

      else if (cmdString.equals("retu")) // should be return, but only first 4
      // characters accepted
      {
        func = line.substring(7);

        if (callStack.isEmpty()) { //return from function that was never called
          System.out.println("Invalid trace at line " + lineNumber);

          System.out.println("Returning from " + func +
              " which was not called");
          stackTrace(callStack);
          stopProgram = true;
          input.close();
          break;
        }

        else { //look at top of stack and pop it
          String topStack = callStack.peek();

          if (topStack.equals(func)) {
            callStack.pop();
            curr_depth--;
          }

          else if (!topStack.equals(func)) { //return from incorrect function
            //also displays correct function to return from
            System.out.println("Invalid trace at line " + lineNumber);
            System.out.println("Returning from " + func + " instead "
                + "of " + topStack);
            stackTrace(callStack);
            stopProgram = true;
            input.close();
            break;
          }

        }

      }
    }

    //if the callStack is not empty at the end, return error
    if ((stopProgram == false) && !callStack.empty()) {
      System.out.println("Invalid trace at line " + lineNumber);
      System.out.println("Not all functions returned");
      stopProgram = true;
      stackTrace(callStack);

    }

    else if (stopProgram == false) {
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth);
    }
  }

  public static void stackTrace(Stack<String> callStack) {
    System.out.println("Stack trace:");
    while (!callStack.empty()) {
      System.out.println(callStack.pop());
    }

  }
}
