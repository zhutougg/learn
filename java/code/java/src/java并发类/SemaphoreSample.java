
import java.util.concurrent.Semaphore;

/**
 * @describe:
 * @creat_user: hyson
 * @creat_date: 2019/3/30 18:00
 **/
public class SemaphoreSample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SemaphoreWorks(semaphore));
            t.start();
        }
    }
}

class SemaphoreWorks implements Runnable {
    private Semaphore semaphore;
    private String name;

    public SemaphoreWorks(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " + msg);
    }

    @Override
    public void run() {
        try {
            log("is waiting for a permit");
            semaphore.acquire();
            log("acquired a permit");
            log("execute!");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log("release a permit");
            semaphore.release();
        }
    }
}
