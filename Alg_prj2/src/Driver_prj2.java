
/**
  * file: Driver_prj2.java
  * author: Paul Ippolito
  * course: CMPT 435L
  * version: 1.0
  * 
  * This file has the logic for the word melt
  */
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Driver_prj2 {
  public static void main(String[] args) {

    // create a new arrayQueue 
    ArrayQueue arrayQueue = new ArrayQueue();

    // creates a new maze
    Maze maze = new Maze();

    // default value for our end check
    boolean isDone = false;
    Scanner input = new Scanner(System.in);

    // create a map containing 2 location
    Map<Location, Location> treeMap = new TreeMap<>();

    // Stream in maze values
    maze.streamIn(input);

    // Set the first to the starting values to prep for nextNeighbor
    maze.getStartLocation().start();

    // add starting location to the queue
    arrayQueue.add(maze.getStartLocation());

    // add the starting location to the map
    treeMap.put(maze.getStartLocation(), maze.getStartLocation());

    // While we aren't done looking in the queue
    while ((!arrayQueue.isEmpty()) && (!isDone)) {

      // if the current work is the same and our end word we are done
      if (maze.isEndLocation(arrayQueue.getFront())) {

        // reverse our queue and print it out
        Stack<String> upsidownStack = new Stack<String>();
        System.out.println("Solution found:");
        upsidownStack.push(arrayQueue.getFront().word);
        Location top = treeMap.get(arrayQueue.getFront());
        //make sure our word and the front of the queue aren't the same
        if (top.word != arrayQueue.getFront().word) {
          upsidownStack.push(top.word);
        }
        // loop through the map and put the part of the map on the stack
        while (top != treeMap.get(top)) {
          top = treeMap.get(top);
          upsidownStack.push(top.word);
        }
        // print out the stack
        while (!upsidownStack.isEmpty()) {
          System.out.println(upsidownStack.pop());
        }
        // Finished Program!
        isDone = true;

      }
      //If we've checked all possible word changes, remove the word
      else if (arrayQueue.getFront().isDone()) {
        arrayQueue.remove();
      }

      // Everything else if the program hasn't finished checking words
      else if (!arrayQueue.getFront().isDone()) {

        // create a new word
        Location neighbor = new Location();

        // run neighbor through all possibilities
        neighbor = arrayQueue.getFront().nextNeighbor();

        // if the maze contains neighbor
        if (maze.isValidLocation(neighbor)){
          //if the map doesn't contain the neighbor
            if(!treeMap.containsKey(neighbor)) {

            // Add neighbor to the map, linking to the front of the queue
            treeMap.put(neighbor, arrayQueue.getFront());
  
            // set neighbor to the starting values
            neighbor.start();
  
            // add the neighbor location to the arrayQueue
            arrayQueue.add(neighbor);
          }
        }
      }

    }
    // Found no answer!
    if (!isDone) {
      System.out.println("No solution found");
    }

  }
}
