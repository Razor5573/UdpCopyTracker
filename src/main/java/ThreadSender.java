import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ThreadSender implements Runnable {
    private UUID uuid = UUID.randomUUID();
    private int i = 0;

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            Sender.sendAppData(socket, args, uuid.toString());
            Sender.sendAppData(socket, args, "I'm alive" + uuid);
            try {
                sleep(5000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 100){
                try {
                    socket.leaveGroup(InetAddress.getByName(args[0]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentThread().interrupt();
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
