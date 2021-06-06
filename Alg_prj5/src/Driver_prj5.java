import java.util.Scanner;
import java.util.ArrayList;


/*CMPT 435
 * Project 5 -- Real-time batch operating system simulator
 * Filename: Driver_prj5.java
 * Student name: Paul Ippolito
 * Course: CMPT 435L
 * Due Date: 4/17/2019
 * Version:1.0
 *
 * This file contains the driver for the Real-time batch operating 
 * system simulator project, as well as the logic for it. The program will read
 * in the ids, processes, deadlines, and other variables from Process.java and
 * ArrayHeap.java.
 */

public class Driver_prj5 {
  
  public static void main(String[] args) {
    
    // system clock int. Keeps track of ticks
    int sysClock = 0;
    
    // new Scanner
    Scanner input = new Scanner(System.in);
    
    // new ArrayHeap
    ArrayHeap myHeap = new ArrayHeap();
    
    int idNum = -1;
    
    int nextProcessStart = 0;
    
    //int secondProcessStart = 0;
    
    Process tempProcess = null;
    
    //boolean batchFlag = false;
    
    //ArrayList<Process> processBatch = new ArrayList<Process>();
    
    int numRun = 0;
    
    int numSkip = 0;
    
    //Process processHolder = null;

    int n = input.nextInt();
    
    int numProcess = n;
    
    if (input.hasNext()) {
      
      nextProcessStart = input.nextInt();
      
      // as long as there are processes
      
      while (numProcess > 0) {
        
        if (myHeap.getNumItems() == 0) {
          
          if (nextProcessStart > sysClock) {
            
            sysClock = nextProcessStart;
          }
        }
        
        // as long as there's a new process to start
        while (nextProcessStart <= sysClock && input.hasNext()) {
          
          idNum = idNum + 1;
          tempProcess = new Process(idNum);
          tempProcess.streamIn(input);
          myHeap.insert(tempProcess);
          n = n - 1;
          
          // start next process to next input
          if (n > 0) {
            nextProcessStart = input.nextInt();
          }
          
          else{
            break;
          }
        }
        
        // when you get the minItem and can complete the process
        if (myHeap.getMinItem().canComplete(sysClock)) {
          
          // run the process
          sysClock = myHeap.getMinItem().run(sysClock);
          myHeap.removeMinItem();
          numProcess = numProcess - 1;
          numRun = numRun + 1;
          
        }
        
        // skip the process
        else {
          System.out.println("skipping process id " + 
              myHeap.getMinItem().getId() + " at " + sysClock);
          
          numSkip = numSkip + 1;
          sysClock = sysClock + 1;
          myHeap.removeMinItem();
          numProcess = numProcess - 1;
        }
      }
    }
    
    // final print out of the processes
    System.out.println("final clock is                 " + sysClock);
    System.out.println("number of processes run is     " + numRun);
    System.out.println("number of processes skipped is " + numSkip);
  }
}