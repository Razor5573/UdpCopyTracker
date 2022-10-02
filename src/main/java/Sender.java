import java.io.IOException;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class Sender {
    public static void sendAppData(MulticastSocket udpSocket, String[] args, String message) {
        DatagramPacket datagram = null;

        try {
            datagram = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(args[0]), 8888);
        } catch (UnknownHostException exception) {
            System.out.println("Failed to create UDP datagram");
        }
        try {
            udpSocket.send(datagram);
        } catch (IOException exception) {
            System.out.println("Failed to send message");
        }
    }
}
