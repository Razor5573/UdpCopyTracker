import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Creator {
    public static MulticastSocket createMulticastSocket(String[] args) {
        MulticastSocket multicastSocket = null;
        try {
            multicastSocket = new MulticastSocket(8888);
        } catch (IOException exception) {
            System.out.println("Failed to open multicast socket.");
        }

        try {
            multicastSocket.joinGroup(InetAddress.getByName(args[0])); // не забыть закрыть
        } catch (IOException exception) {
            System.out.println("Failed to join to the multicast group");
        }

        return multicastSocket;
    }
}
