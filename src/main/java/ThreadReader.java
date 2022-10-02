import java.net.MulticastSocket;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadReader implements Runnable {
    private MulticastSocket socket;
    private int i = 0;
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            Reader.readMulticastDatagram(socket);
            try {
                sleep(5000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 100){
                currentThread().interrupt();
            }
        }
    }

    public ThreadReader(String[] args) {
        this.socket = Creator.createMulticastSocket(args);
    }
}
