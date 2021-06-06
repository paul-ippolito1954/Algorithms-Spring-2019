
/**
 * file: Maze.java
 * author: Paul Ippolito
 * course: CMPT 435L
 * version: 1.0
 * 
 * This file contains the declaration of the 
 * Maze constructor and the subsequent methods.
 */
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Maze
 * 
 * This class implements a Maze constructor that is used in the reading in and
 * sorting of the given Locations.
 */
class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;

  int numberOfLocations;

  /**
   * Maze()
   * 
   * creates new TreeSet of Locations and initializes variables
   */
  Maze() {

    validLocations = new TreeSet<Location>();
    startLocation = null;
    endLocation = null;

    numberOfLocations = 0;
  }

  /**
   * getStartLocation()
   * 
   * returns starting location of the maze given
   */
  Location getStartLocation() {
    return startLocation;
  }

  /**
   * isValidLocation()
   * 
   * Checks TreeSet to see if current word is in the set
   */
  boolean isValidLocation(Location loc) {

    if (validLocations.contains(loc)) {
      return true;
    }
    return false;
  }

  /**
   * isEndLocation()
   * 
   * Checks if current word is ending word of maze
   */
  boolean isEndLocation(Location loc) {
    if (endLocation.word.equals(loc.word)) {
      return true;
    }
    return false;
  }

  /**
   * isEndLocation()
   * 
   * This reads variables in order to populate the maze
   * 
   * 
   */
  void streamIn(Scanner input) {

    Location location = new Location();

    numberOfLocations = input.nextInt();

    for (int i = 0; i < numberOfLocations; i++) {

      location = new Location();
      location.streamIn(input);

      validLocations.add(location);
    }

    startLocation = new Location();
    endLocation = new Location();
    startLocation.streamIn(input);
    endLocation.streamIn(input);
  }
}