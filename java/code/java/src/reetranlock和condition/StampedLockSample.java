import java.util.concurrent.locks.StampedLock;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/3/30 14:37
 **/
public class StampedLockSample {
    private final StampedLock stampedLock = new StampedLock();
    private double x, y;

    void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    double distance() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void move2(double newx, double newy) {
        long stamp = stampedLock.readLock();
        try {
            while (x == 0 && y == 0) {
                long ws = stampedLock.tryConvertToWriteLock(stamp);
                if (ws != 0) {
                    stamp = ws;
                    x = newx;
                    y = newy;
                    break;
                } else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            stampedLock.unlock(stamp);
        }

    }
}
