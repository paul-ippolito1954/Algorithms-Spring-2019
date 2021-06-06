/**
 * file: ArrayQueue.java
 * author: Paul Ippolito
 * course: CMPT 435L
 * version: 1.0
 * 
 * Contains ArrayQueue constructor and subsequent methods
 */

/**
 * ArrayQueue
 * 
 * This class implements an ArrayQueue constructor that is used in the creation
 * of a queue to add the valid words found in Maze and to get the location
 * objects in the queue. Circular array used to accomplish task.
 */
class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;

  /**
   * doubleCapcity()
   *
   * If our queue ever becomes full and we then need to add another location:
   * double capacity the location reassign our values to the doubleCap array.
   */
  private void doubleCapacity() {
    Location[] doubleCap = new Location[data.length * 2];
    for (int scan = 0; scan < capacity; scan++) {
      doubleCap[scan] = data[front];
      front = (front + 1) % data.length;
    }

    front = 0;
    length = capacity;
    data = doubleCap;

  }

  /**
   * ArrayQueue()
   *
   * This constructor defines a new ArrayQueue with a default length, starting
   * capacity, default array size, location of the front in the array and the
   * current count of objects in the array index and next letter.
   *
   */
  ArrayQueue() {

    length = 0;
    capacity = 0;
    data = new Location[1];
    front = 0;

  }

  /**
   * ArrayQueue()
   *
   * This function defines a new copy constructor of ArrayQueue with a with the
   * defaults being the same as the same as the starting Constructor.
   * 
   * Parameters: q: a ArrayQueue to copy the new values into.
   *
   */
  ArrayQueue(ArrayQueue q) {
    q.data = data;
    q.length = length;
    q.capacity = capacity;
    q.front = front;

    q.capacity = capacity;
  }

  /**
   * add()
   *
   * This function first checks to see if our current count is the same as our
   * current max length, if it is, we double the size of our array. It then adds
   * the location into the array at the next available location.
   *
   * Parameters: loc: the location to add into our array.
   *
   */
  void add(Location loc) {
    if (capacity == data.length) {
      doubleCapacity();
    }
    data[length] = loc;

    length = (length + 1) % data.length;

    capacity++;
  }

  /**
   * remove()
   *
   * This function first checks to see if our current count is empty if it is,
   * we print out an error. Otherwise, it sets the front of our array to null,
   * reassigns the front and reduces the count in our array by one.
   *
   */
  void remove() {

    data[front] = null;
    front = (front + 1) % data.length;
    capacity--;

  }

  /**
   * getFront()
   *
   * This function first checks to see if our current count is empty if it is,
   * we print out an error and return null. Otherwise, it returns the front of
   * the array.
   * 
   * Return Value: the front of the array if the arry is not empty, null if it
   * is.
   *
   */
  Location getFront() {
    return data[front];
  }

  /**
   * getLength()
   *
   * Returns the amount of Locations we have in the array.
   * 
   * Return Value: the amount of Locations in the array.
   *
   */
  int getLength() {
    return capacity;
  }

  /**
   * isEmpty()
   *
   * Checks to see if our array is empty.
   * 
   * Return Value: true if the front of the array is empty, false if it is not.
   *
   */
  boolean isEmpty() {
    if (data[front] == null) {
      return true;
    } 
    
    else {
      return false;
    }
  }

  /**
   * copyFrom()
   *
   * Tells us to get a deep copy of the read in array and return said array.
   * 
   * Parameters: q: the ArrayQueue to read in.
   * 
   * Return Value: the newly deep copied ArrayQueue.
   *
   */
  ArrayQueue copyFrom(ArrayQueue q) {
    q.data = data;
    q.length = length;
    q.capacity = capacity;
    q.front = front;

    return q;
  }
}
