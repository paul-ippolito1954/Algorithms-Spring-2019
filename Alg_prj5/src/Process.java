import java.util.Scanner;

/* File: Process.java
 * Author: Paul Ippolito
 * Course: CMPT 435L
 * Version: 1.0
 * 
 * A Process object represents a batch-executed computer process, which has a
 * hard real-time deadline before which it must finish. It also has an amount of
 * time that it requires to execute.
 *
 * A Process object has a (unique) id, which starts at 0 and should increment
 * whenever a new process is created. It is up to the operating system to assign
 * it an ID.
 *
 * Finally, a Process object has a data member "information", which is printed
 * when the run() method executes.
 *
 * The default constructor for the Process initializes all the data members to
 * reasonable defaults, and the id to the given ID (default is 0).
 *
 * The run method prints out the information contained in this process, and
 * then returns the system time plus the required time (which is the new system
 * time).
 *
 * The canComplete method returns true if the method would be able to complete
 * if it starts now, or false if it could not finish by its deadline.
 *
 * The getId method returns the id of this Process object.
 *
 * The isLess() method is used to order Processes by:
 *  1. deadline (least to greatest)
 *  2. required time (least to greatest, if deadlines are equal)
 *  3. id (least to greatest, if deadlines & required times are equal)
 * The isLess() method returns true if (this) < p, false otherwise.
 *
 * The streamIn() method is used to read in the deadline, requiredTime, and
 * information from a Scanner input stream.
 */

class Process {
  
  private int id;
  private int deadline;
  private int requiredTime;
  private String information;
  
  public Process(int theId) {
    this.id = theId;
    this.deadline = 0;
    this.requiredTime = Integer.MAX_VALUE;
    this.information = "";
  }
  
  public Process(Process P) {
    this.id = P.id;
    this.deadline = P.deadline;
    this.requiredTime = P.requiredTime;
    this.information = P.information;
  }
  
  public int run(int currentSystemTime) {
    int newSystemTime = this.requiredTime + currentSystemTime;
    System.out.println("running process id " + this.id + " at " +
      currentSystemTime);
    System.out.println(this.information);
    return newSystemTime;
  }
  
  public boolean canComplete(int currentSystemTime) {
    return ((currentSystemTime + this.requiredTime) <= this.deadline);
  }
  
  // retrieves id of process object and returns it
  public int getId() {
    return this.id;
  }
  
  // if current dealine is less than process deadline, return true
  public boolean isLess(Process p) {
    if (this.deadline < p.deadline){
      return true;
    }
    
    // sorts between required time and id if they have the same deadline
    else if (this.deadline == p.deadline){
      if (this.requiredTime < p.requiredTime){
        return true;
      }
      
      else if (this.requiredTime == p.requiredTime){
        if (this.getId() < p.getId()){
          return true;
        }
      }
    }
    
    return false;
  }
  
  // Like every stream-in, reads in all input from user for subsequent 
  // variables
  public void streamIn(Scanner input) {
    this.deadline = input.nextInt();
    this.requiredTime = input.nextInt();
    String tempInfo = input.nextLine();
    this.information = tempInfo.substring(1, tempInfo.length());
  }
  
  
  @java.lang.Override
  public java.lang.String toString() {
    return "Process{" +
      "id=" + id +
      ", deadline=" + deadline +
      ", requiredTime=" + requiredTime +
      ", information='" + information + '\'' +
      '}';
  }
}