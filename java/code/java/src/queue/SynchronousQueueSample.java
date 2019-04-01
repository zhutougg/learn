import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/1 10:40
 **/
public class SynchronousQueueSample {
    public static void main(String[] args) {
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>(true);
        SynchronousQueueProducer synchronousQueueProducer1 = new SynchronousQueueProducer(synchronousQueue);

        new Thread(synchronousQueueProducer1).start();
        SynchronouseConsumer synchronouseConsumer1 = new SynchronouseConsumer(synchronousQueue);
        new Thread(synchronouseConsumer1).start();

        SynchronouseConsumer synchronouseConsumer2 = new SynchronouseConsumer(synchronousQueue);
        new Thread(synchronouseConsumer2).start();
    }
}

class SynchronousQueueProducer implements Runnable {
    private BlockingQueue<String> blockingDeque;

    public SynchronousQueueProducer(BlockingQueue<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }


    @Override
    public void run() {
        while (true) {
            try {
                String data = UUID.randomUUID().toString();
                System.out.println("put " + data);
                blockingDeque.put(data);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SynchronouseConsumer implements Runnable {
    private BlockingQueue<String> blockingDeque;

    public SynchronouseConsumer(BlockingQueue<String> blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " take(): " + data);
        }
    }
}
