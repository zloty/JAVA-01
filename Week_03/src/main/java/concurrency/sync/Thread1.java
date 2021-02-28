package concurrency;

public class Thread1 implements Runnable {
  private Object obj;
  Thread1(Object obj){
    this.obj = obj;
  }
  @Override
  public synchronized void run() {
//    synchronized (this) {
      for (int i = 0;i < 1000;i ++) {
        System.out.println(Thread.currentThread().getName() + " synchronized loop" + i);
      }
//    }
  }

  public static void main(String[] args) {
    Thread1 t1 = new Thread1(new Object());
    Thread ta = new Thread(t1,"A");
    Thread tb = new Thread(t1,"B");
    ta.start();
    tb.start();

  }
}
