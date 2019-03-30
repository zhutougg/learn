import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/3/30 12:06
 **/
public class RWSample {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
    private final Map<String, String> m = new HashMap<>();

    public String get(String key) {
        r.lock();
        System.out.println("读锁定！");
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public void write(String key, String value) {
        w.lock();
        System.out.println("写锁定！");
        try {
            m.put(key, value);
        } finally {
            r.unlock();
        }
    }
}
