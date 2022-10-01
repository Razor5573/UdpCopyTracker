import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class ThreadSender implements Runnable {
    @Override
    public synchronized void run() {
        notifyAll();
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        String startAppTime = new SimpleDateFormat("HH:mm:ss").format(date);
        startAppTime = startAppTime + ";";
        while (true) {
            Sender.sendAppData(socket, args, uuid.toString());
            Sender.sendAppData(socket, args, startAppTime);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private MulticastSocket socket;
    private String[] args;

    public ThreadSender(String[] args) {
        this.socket = Creator.createMulticastSocket(args);
        this.args = args;
    }

}
