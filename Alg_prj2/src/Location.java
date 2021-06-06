
/**
 /*
 * File: Location.java
 * Author: Paul Ippolito
 * Course: CMPT 435L
 * Version 1.0
 */

import java.util.Scanner;
import java.lang.StringBuilder;

/* Changes from project 1:
 *
 * The Location class now represents a word, instead of a row/column position.
 *
 * The iteration methods start() and nextNeighbor() are no longer const methods.
 * However, they still serve the same purposes of starting and continuing
 * iteration. Note that neither method should modify the "word" data member; 
 * they should only modify the two iteration-related data members.
 *
 * Consider a Location object called 'x'. Repeatedly calling x.nextNeighbor()
 * should produce all Locations that are different from x by doing one of the
 * following things:
 *  1. changing a letter in x
 *  2. inserting a new letter in x
 *  3. deleting a letter from x
 * The nextNeighbor method should never produce x itself (x is not a neighbor of
 * itself). The order of iteration should be as follows:
 *  1. Change each letter of x to a different letter, from the first letter to
 *     the last (i.e. x.word[0], x.word[1], ...). Change each letter in alphabet
 *     order (i.e. a, b, c, ..., z).
 *  2. Insert a letter at each position of x, from the first position to the
 *     last (i.e. before x.word[0], before x.word[1], etc.). Try each possible
 *     letter in alphabet order.
 *  3. Delete a letter from each position of x, from the first letter to the
 *     last (i.e. x.word[0], x.word[1], etc.);
 *  After performing all these steps, iteration will be done.
 *
 * As an example, here is the way iteration should work on the word "and"
 * (read the first row left to right, then the second row, etc.):
 *  bnd, cnd, dnd, end, fnd, gnd, hnd, ind, jnd, knd, lnd, mnd, nnd, ond, pnd,
 *  qnd, rnd, snd, tnd, und, vnd, wnd, xnd, ynd, znd, aad, abd, acd, add, aed,
 *  afd, agd, ahd, aid, ajd, akd, ald, amd, aod, apd, aqd, ard, asd, atd, aud,
 *  avd, awd, axd, ayd, azd, ana, anb, anc, ane, anf, ang, anh, ani, anj, ank,
 *  anl, anm, ann, ano, anp, anq, anr, ans, ant, anu, anv, anw, anx, any, anz,
 *  aand, band, cand, dand, eand, fand, gand, hand, iand, jand, kand, land,
 *  mand, nand, oand, pand, qand, rand, sand, tand, uand, vand, wand, xand,
 *  yand, zand, aand, abnd, acnd, adnd, aend, afnd, agnd, ahnd, aind, ajnd,
 *  aknd, alnd, amnd, annd, aond, apnd, aqnd, arnd, asnd, atnd, aund, avnd,
 *  awnd, axnd, aynd, aznd, anad, anbd, ancd, andd, aned, anfd, angd, anhd,
 *  anid, anjd, ankd, anld, anmd, annd, anod, anpd, anqd, anrd, ansd, antd,
 *  anud, anvd, anwd, anxd, anyd, anzd, anda, andb, andc, andd, ande, andf,
 *  andg, andh, andi, andj, andk, andl, andm, andn, ando, andp, andq, andr,
 *  ands, andt, andu, andv, andw, andx, andy, andz, nd, ad, an
 * Notice that the word "and" does not appear in this list.
 *
 * The isLess method provides a means of comparing two Locations for purposes of
 * keeping them in order. This is useful for sorting Locations and for putting
 * them in an ordered collection, like a binary search tree, or Java set or
 * map.  The isLess method should compare based on the word only, and not on
 * iteration status.
 *
 * Some comments on the data members:
 *  - "word" contains, of course, the string that this Location represents.
 *
 *  - "iterationMode" should reflect which mode the iteration is currently in,
 *    which is one of CHANGE_LETTER, INSERT_LETTER, DELETE_LETTER, or DONE.
 *
 *  - "indexToChange" contains the index into "word" that should be changed next
 *    (on the next call to nextNeighbor()). This is used repeatedly for each 
 *    mode.
 *
 *  - "nextLetter" contains the character that should be used next to change or
 *    insert (for the modes CHANGE_LETTER and INSERT_LETTER) on the next call to
 *    nextNeighbor().
 */

/**
 * Location
 * 
 * Location constructor used to assign Maze Locations in Maze.java and
 * Location.java
 */
class Location implements Comparable<Location> {
  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;
  boolean equalSwitch = false;

  /**
   * Location
   *
   * This constructor with new Location, default word, and iteration
   *
   */
  Location() {
    // -
    word = "";
    iterationMode = DONE;
    indexToChange = 0;
    nextLetter = ' ';

  }

  /**
   * Start
   *
   * Assigns location start values
   */
  void start() {
    // -
    iterationMode = CHANGE_LETTER;
    nextLetter = 'a';
  }

  /**
   * nextNeighbor
   *
   * Creates neighbor location to be tested against iterationModes
   * and is manipulated based on iteration. Changes, Inserts, and deletes 
   * characters until it matches a valid word from ArrayQueue.java
   * 
   * Return Value: neighbor Location with edited word
   */
  Location nextNeighbor() {
    // -

    // create new location
    Location neighbor = new Location();

    // set neighbor's word to our given word
    neighbor.word = word;

    // StringBuilder used to track/manipulate word
    StringBuilder neighborString = new StringBuilder(neighbor.word);

    if (iterationMode == CHANGE_LETTER) {

      // if current character at current index is same as letter we change to
      if (!(neighbor.word.charAt(indexToChange) == nextLetter)) {

        // new String set to nextletter
        String nextLetterString = String.valueOf(nextLetter);

        // replaces our word with new character
        neighbor.word = neighborString.replace(indexToChange,
            indexToChange + 1, nextLetterString).toString();
      }

      // increase the letter to change, so a turns to b, etc
      nextLetter++;

      // if letter is passed z and we're passed the index of word
      if ((nextLetter > 'z') && !(indexToChange >= word.length() - 1)) {
        indexToChange++;
        nextLetter = 'a';
        // increase index and go back to a
      } else if ((nextLetter > 'z') && (indexToChange >= word.length() - 1)) {
        nextLetter = 'a';
        indexToChange = 0;
        iterationMode = INSERT_LETTER;

      }
    }

    // trying to insert new letters
    else if (iterationMode == INSERT_LETTER) {

      // insert new letter and set word to new string with new letter
      neighborString.insert(indexToChange, nextLetter);
      neighbor.word = neighborString.toString();
      nextLetter++;

      // reset variables and continue
      if ((nextLetter > 'z') && !(indexToChange >= word.length())) {
        indexToChange++;
        nextLetter = 'a';
      }

      // reset variables and move on to delete
      else if ((nextLetter > 'z') && (indexToChange >= word.length())) {
        nextLetter = 'a';
        indexToChange = 0;
        iterationMode = DELETE_LETTER;
      }

    }

    // deleting the characters
    else if (iterationMode == DELETE_LETTER) {
      // deletes character and sets word to new word without deleted character
      neighborString.delete(indexToChange, indexToChange + 1);
      neighbor.word = neighborString.toString();

      if (!(indexToChange >= word.length() - 1)) {
        indexToChange++;
      } else {
        iterationMode = DONE;
      }

    }

    return neighbor;
  }

  /**
   * isDone()
   *
   * Returns true if the iterationMode is DONE
   * 
   * Return Value:boolean to indicate we've finished output of manipulated
   * words for the inserted words
   */
  boolean isDone() {
    // -
    if (iterationMode == DONE) {
      return true;
    } 
    
    else {
      return false;
    }

  }

  /**
   * isEqual()
   *
   * returns true if current location has the same word as the
   * called location
   * 
   * Return Value: true if they have the same values, false if not.
   */
  boolean isEqual(Location loc) {
    // -
    if (loc.word == this.word) {
      return true;
    } 
    
    else {
      return false;
    }
  }

  /**
   * streamOut()
   *
   * Prints out called locations
   * 
   */
  void streamOut() {
    // -
    System.out.println(word);
  }

  /**
   * streamIn()
   *
   * Reads the next input value into word
   * 
   * 
   * 
   */
  void streamIn(Scanner input) {
    // -
    word = input.next();
    // System.out.println(word);
  }

  /**
   * isLess()
   *
   * Checks to see if our first location is less than the second
   * 
   * 
   * 
   */
  boolean isLess(Location loc) {
    // -
    int lessThanIndex = 0;
    String firstWord = this.word;
    String secondWord = loc.word;

    while (true) {

      if (firstWord.charAt(lessThanIndex) < secondWord.charAt(lessThanIndex)) {
        return true;
      }

      else if (firstWord.charAt(lessThanIndex) == secondWord
          .charAt(lessThanIndex)) {
        if ((lessThanIndex >= firstWord.length() - 1)
            && (!(lessThanIndex >= secondWord.length() - 1))) {
          return true;
        }

        else if ((!(lessThanIndex >= firstWord.length() - 1))
            && ((lessThanIndex >= secondWord.length() - 1))) {
          return false;
        } else if ((lessThanIndex >= firstWord.length() - 1)
            && ((lessThanIndex >= secondWord.length() - 1))) {
          equalSwitch = true;
          return false;
        }
        lessThanIndex++;
      } else if (firstWord.charAt(lessThanIndex) > secondWord
          .charAt(lessThanIndex)) {
        return false;
      }

    }
  }

  public int compareTo(Location loc) {

    boolean temp = this.isLess(loc);

    if (temp == true) {

      return -1; // Places it 1 place before
    } 
    
    else if (equalSwitch == true) {

      equalSwitch = false; // has to be reset, otherwise ALWAYS TRUE;
      return 0;

    } 
    
    else {
      return 1;
    }
  }

}
