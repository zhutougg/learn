import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/3/30 18:38
 **/
public class CyclicBarrierSample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Action ... Go ");
            }
        });

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CyclicBarrierWork(cyclicBarrier));
            thread.start();
        }
    }
}

class CyclicBarrierWork implements Runnable {
    private String name;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWork(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            log("execute!");
            try {
                cyclicBarrier.await();
                log("await!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " + msg);
    }
}
