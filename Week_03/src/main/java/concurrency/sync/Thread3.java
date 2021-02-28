package concurrency;

public class Thread3 {
  class Inner {
    private void mrt1() {
      int i = 5;
      while (i -- > 0) {
        System.out.println(Thread.currentThread().getName() + " : Inner.mrt1()=" + i);
      }
    }

    private void mrt2() {
      int i = 5;
      while (i -- > 0) {
        System.out.println(Thread.currentThread().getName() + " : Inner.mrt2()=" + i);
      }
    }
  }

  private void mrt3(Inner inner) {
    synchronized (inner) {
      inner.mrt1();
    }
  }

  private void mrt4(Inner inner) {
//    synchronized (inner) {
      inner.mrt2();
//    }
  }

  public static void main(String[] args) {
    final Thread3 thread3 = new Thread3();
    final Inner inner = thread3.new Inner();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        thread3.mrt3(inner);
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        thread3.mrt4(inner);
      }
    });

    t1.start();
    t2.start();
  }
}
