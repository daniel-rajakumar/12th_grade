public class Customer implements Comparable<Customer>{

  private static int idGen = 0;

  private int id;
  private boolean priority;
  private long callStart;
  private long holdTime;

  public Customer(boolean priority){
    this.priority = priority;
    id = idGen++;
    callStart = System.currentTimeMillis();
  }

  public long getCallStart() { return callStart; }

  public long getHoldTime() { return holdTime; }

  public int getID() { return id; }

  public boolean getPriority() { return priority;}

  public void setHoldTime(){
    holdTime = System.currentTimeMillis() - callStart;
  }

  public int compareTo(Customer other) {
    if (this.priority && !other.priority)
      return -1;
    if (!this.priority && other.priority)
      return 1;

    if (this.callStart < other.callStart)
      return -1;
    return 1;
    
  }

  public String toString(){
    return id + ", priority =" + priority;
  }

}