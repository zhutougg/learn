import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/3/30 11:35
 **/
public class ReetranklockSample {
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ReetranklockSample reetranklockTest = new ReetranklockSample();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始singal");
                reetranklockTest.signal();
            }
        }.start();
        reetranklockTest.getLock().lock();
        long currentTime = System.currentTimeMillis();
        System.out.println("开始await");
        reetranklockTest.await();
        System.out.println(String.format("结束await %s", System.currentTimeMillis() - currentTime));
        reetranklockTest.lock.unlock();
    }

    private void signal() {
        lock.lock();
        condition.signal();
        lock.unlock();
    }

    private void await() throws InterruptedException {
        condition.await();
    }

    private ReentrantLock getLock() {
        return lock;
    }


}
