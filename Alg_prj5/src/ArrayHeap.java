/*CMPT 435
 * Project 5 -- Real-time batch operating system simulator
 * Filename: ArrayHeap.java
 * Student name: Paul Ippolito
 * Version:1.0
 *
 *
 */

import java.util.Arrays;

/* An ArrayHeap is a binary heap that is implemented using arrays. The heap can
 * grow dynamically if it gets full (it should double in capacity each time it
 * gets full).
 *
 * The default constructor constructs an empty ArrayHeap.
 *
 * The copy constructor creates a logical copy of the given ArrayHeap,
 * storing the values in its own copy. It should resize the heap as necessary 
 * so that the two have the same capacity.
 *
 * The insert method inserts the given item into the heap (at the bottom),
 * and then restores the heap order property by bubbling the item up.
 *
 * The removeMinItem method removes the minimum item at the root of the
 * heap, places the last element in the root's position, and bubbles it
 * down to restore the heap property.
 *
 * The getMinItem method returns the minimum item at the top of the heap.
 *
 * The getNumItems method returns the number of items that are on the heap.
 *
 * The doubleCapacity method doubles the capacity of the heap (the data and
 * heapAndFreeStack data members).
 *
 * The bubbleUp method can be implemented iteratively or recursively. It
 * starts at the item given by ndx, and moves it up the heap towards the
 * root in order to preserve the heap order property.
 *
 * The bubbleDown method can be implemented iteratively or recursively. It
 * starts at the item given by ndx, and moves it down the heap towards the
 * leaves in order to preserve the heap order property.
 *
 * The data member "data" contains the items that are stored in the heap.
 * Once an item is placed in data, it should not move.
 *
 * The data member "heapAndFreeStack" describes the structure of the heap (in
 * heapAndFreeStack[0] through heapAndFreeStack[numItems-1]), and it has a stack
 * of the indexes in data that are not being used (in heapAndFreeStack[numItems]
 * through heapAndFreeStack[capacity-1]).  Every value in this array (whether in
 * the heap or stack region) is an index into the array "data".
 *
 * The data member "numItems" contains the number of items on the heap, and
 * serves as the dividing line between the heap structure and the stack of free
 * items.
 *
 * The data member "capacity" represents the number of items that the heap can
 * contain before requiring extra space. The data and heapAndFreeStack arrays
 * both are capacity long.
 */

class ArrayHeap {
  private Process[] data;
  private int[] heapAndFreeStack;

  private int numItems;
  private int capacity;

  public ArrayHeap() {
    this.numItems = 0;
    this.capacity = 1;
    data = new Process[capacity];
    heapAndFreeStack = new int[capacity];
  }
  public ArrayHeap(ArrayHeap h) {
    // -
  }

  //root = 0
  //parent of a node[i] is floor((i-1)/2)
  //children of element[i] are 2(i + 1)-1
  // and 2(i + 1)

  private void doubleCapacity() {
    
    Process[] indata = new Process[this.capacity * 2];
    
    //for loop to move data
    // for I < length
    // get item at front + i
    // if front + i> capacity, mod by capacity
    
    for (int i = 0; i < this.capacity; i++){
      indata[i] = this.data[i];
    }
    
    
    this.data = indata;
    this.capacity = this.capacity * 2;
  }

  private void bubbleUp(int ndx) {
    
    if (ndx == 0){
      return;
    }
    
    int parent = (ndx - 1)/2;

    if (this.data[ndx].isLess(this.data[parent])){
      Process temp1 = this.data[ndx];
      Process temp2 = this.data[parent];
      this.data[ndx] = temp2;
      this.data[parent] = temp1;
      bubbleUp(parent);
    }
  }

  private void bubbleDown(int ndx) {
    int child1 = (ndx * 2) + 1,
        child2 = child1 + 1;

    if ((child1 < this.numItems) &&
      (this.data[child1] != null)){
      
      int lesserChild = child1;
      
      if ((child2 < this.numItems) &&
        (this.data[child2] != null) &&
        (this.data[child2].isLess(this.data[child1]))) {
        lesserChild = child2;
      }
      
      if ((this.data[lesserChild] != null) &&
        (this.data[lesserChild].isLess(this.data[ndx]))){
        
        Process temp1 = this.data[ndx];
        Process temp2 = this.data[lesserChild];
        this.data[ndx] = temp2;
        this.data[lesserChild] = temp1;
        bubbleDown(lesserChild);
      }
    }
  }




  public void insert(Process item) {
    if (this.numItems >= this.capacity){
      this.doubleCapacity();
    }
    this.data[this.numItems] = item;
    this.bubbleUp(this.numItems);
    this.numItems = this.numItems + 1;
  }

  public void removeMinItem() {
    
    //move the last item in the heap to the root
    //bubble the root down to restore heap property
    
    int minCheck = this.capacity - 1;
    Process tempChild = this.data[minCheck];
    while (tempChild == null){
      minCheck = minCheck - 1;
      tempChild = this.data[minCheck];
    }
    
    Process tempo1 = new Process(this.data[0]);
    Process tempo2 = new Process(this.data[minCheck]);

    this.data[0] = tempo2;
    this.data[minCheck] = null;


    this.bubbleDown(0);
    this.numItems = this.numItems - 1;
  }

  // return first item in arrayHeap
  public Process getMinItem() {
    return this.data[0];
  }

  // get the number of items in the ArrayHeap
  public int getNumItems() {
    return this.numItems;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "ArrayHeap{" +
      "data=" + java.util.Arrays.toString(data) +
      ", heapAndFreeStack=" + java.util.Arrays.toString(heapAndFreeStack) +
      ", numItems=" + numItems +
      ", capacity=" + capacity +
      '}';
  }
}