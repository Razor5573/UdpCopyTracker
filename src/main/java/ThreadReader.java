import java.net.MulticastSocket;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadReader implements Runnable {
    private MulticastSocket socket;
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            Reader.readMulticastDatagram(socket);
        }
    }

    public ThreadReader(String[] args) {
        this.socket = Creator.createMulticastSocket(args);
    }
}
