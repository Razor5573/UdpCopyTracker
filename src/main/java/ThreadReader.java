import java.net.MulticastSocket;

import static java.lang.Thread.sleep;

public class ThreadReader implements Runnable {
    private MulticastSocket socket;

    @Override
    public void run() {
        while (true) {
            Reader.readMulticastDatagram(socket);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ThreadReader(String[] args) {
        this.socket = Creator.createMulticastSocket(args);
    }
}
