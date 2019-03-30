import java.util.concurrent.CountDownLatch;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/3/30 18:27
 **/
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWork(countDownLatch));
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWork(countDownLatch));
            t.start();
        }
        while (countDownLatch.getCount() != 1) {
            Thread.sleep(100L);
        }
        System.out.println("第一批执行完成");
        countDownLatch.countDown();
    }
}

class FirstBatchWork implements Runnable {
    private CountDownLatch countDownLatch;

    public FirstBatchWork(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("first batch execute");
        countDownLatch.countDown();
    }
}

class SecondBatchWork implements Runnable {
    private CountDownLatch countDownLatch;

    public SecondBatchWork(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("second batch execute");
    }
}
