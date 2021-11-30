import java.util.*;

class Main {
  public static void main(String[] args) {
     new CustomerQTest();
  }

  public static void delay(long ms){
    try {
      // thread to sleep for ms milliseconds
      Thread.sleep(ms);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
 


}