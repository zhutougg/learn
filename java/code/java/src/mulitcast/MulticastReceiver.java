import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/10 11:27
 **/
public class MulticastReceiver extends Thread {
    MulticastSocket multicastSocket = null;
    byte[] buf = new byte[256];

    public void run() {
        try {
            multicastSocket  = new MulticastSocket(4446);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
