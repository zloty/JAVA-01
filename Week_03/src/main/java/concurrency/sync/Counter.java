package concurrency;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
  public final static int A = 10;
  public static int B = 10;
//  private volatile int number = 0;
  private AtomicInteger number = new AtomicInteger();
  public void incr() {
//    number++;
    number.getAndIncrement();
//    number.incrementAndGet();
  }
  public int getSum() {
//    return number;
    return number.get();
  }

  public static void main(String[] args) {
//    Counter counter = new Counter();
//    counter.incr();
//    System.out.println(counter.getSum());
//
//    Counter counter3 = new Counter();
//    counter3.incr2();
//    System.out.println(counter3.getSum());
//
//
//    try {
//      System.in.read();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    int loop = 100000;

//    for (int i = 0;i < loop;i ++) {
//      counter.incr();
//    }
//    System.out.println(counter.getSum());

    Counter counter2 = new Counter();
    Thread t1 = new Thread(()->{
      for (int i = 0;i < loop >> 1;i ++) {
        counter2.incr();
      }
    });

    Thread t2 = new Thread(()->{
      for(int i =0;i < loop >> 1;i ++) {
        counter2.incr();
      }
    });

    t1.start();
    t2.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(counter2.getSum());
  }
}
