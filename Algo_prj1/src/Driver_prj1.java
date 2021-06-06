

import java.util.Scanner;

/*
 * Name: Driver_prj1.java
 * Author: Paul Ippolito
 * Class: CMPT 435L
 * Version 1.0
 * 
 * This driver is used to initiate the maze finder project 1.
 * it takes in a stack of locations for a maze, which are grid coordinates.
 * it will search for the shortest path using DFS and go along each based on
 * this. This java file calls several other files, Location, LocationStack, and
 * Maze.java in order to carry out its operations. It will create a new stack
 * and maze, stream in the inputs given through the streamIn function, get its
 * start and end locations, and go from there. It will start by checking if
 * there is any way to go right, then down, then left, and finally up. if it
 * finds a new way, it will go that way. If it finds no possible way to move,
 * it returns that it is unable to find a solution to the maze.
 */


public class Driver_prj1 {
  
  public static void main (String[] args) {
    
    //create new stack to read locations in
    LocationStack myStack = new LocationStack();
    
    //maze we will check against
    Maze myMaze = new Maze();
    
    //check if file is done
    boolean done = false;
    
    //read the inputs given
    Scanner input = new Scanner(System.in);
    
    //stream in inputs for maze
    myMaze.streamIn(input);
    
    //get starting locations of maze and set start to Right
    myMaze.getStartLocation().start();
    
    //push start to top of stack
    myStack.push(myMaze.getStartLocation());
    
    //while stack not empty and not done checking locations
    while((!myStack.isEmpty()) && (!done)) {
      //check if curr loc is at top of stack
      //if same as ending location
      
      if (myMaze.isEndLocation(myStack.getTop())) {
        
        myStack.streamOut(myStack);
        done = true;
      }
      
      //if we've gone through all possible locations around current loc
      else if (myStack.getTop().isDone())
        myStack.pop();
      
      else {
        
        //create new neighbor location
        Location neighbor = new Location();
        
        //run get next neighbor from top of stack and push it
        neighbor = myStack.getTop().nextNeighbor();
        
        if (myMaze.isValidLocation(neighbor) && (!myStack.isOn(neighbor))) {
          
            neighbor.start();
            myStack.push(neighbor);
          
        }
      }
    }
    
    //found no possible solution to maze
    if (!done)
      System.out.println("No solution found");
  }
  
  

}
