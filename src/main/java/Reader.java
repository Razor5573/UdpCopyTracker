import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class Reader {
    public static void readMulticastDatagram(MulticastSocket multicastSocket) {
        DatagramPacket receivedDatagram = null;

        try {
            receivedDatagram = new DatagramPacket(new byte[400], 400);
        } catch (IllegalArgumentException exception) {
            System.out.println("Failed to read UDP datagram");
        }

        try {
            multicastSocket.receive(receivedDatagram);
            CopyAdder.addCopy(receivedDatagram);
        } catch (IOException exception) {
            System.out.println("Failed to receive the multicast datagram");
        }
    }
}
