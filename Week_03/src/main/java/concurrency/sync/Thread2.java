package concurrency;

public class Thread2 {
  public void mrt1() {
    synchronized (this) {
      int i = 5;
      while(i-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + i);
      }
    }
  }

  public void mrt2() {
    synchronized (this) {
      int i = 5;
      while (i-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + i);
      }
    }
  }

  public static void main(String[] args) {
    Thread2 thread2 = new Thread2();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        thread2.mrt1();
      }
    },"t1");

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        thread2.mrt2();
      }
    },"t2");

    t1.start();
    t2.start();
  }
}
