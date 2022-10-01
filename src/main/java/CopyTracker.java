import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;

public class CopyTracker {
    private static HashMap<String, String> appCopies = new HashMap<>();
    private static HashMap<String, InetAddress> IPOfCopies = new HashMap<>();
    private static String startAppTime = null;
    private static String uuid = null;
    private static int isAliveCounter = 0;

    public static void trackNewCopy(DatagramPacket receivedDatagram) {
        String data = new String(receivedDatagram.getData()).trim();

        if (data.endsWith(";")) {
            startAppTime = data;
        } else {
            uuid = data;
        }

        if (startAppTime != null && uuid != null) {
            appCopies.put(uuid, startAppTime);
            IPOfCopies.put(uuid, receivedDatagram.getAddress());
        }

        for (String key : appCopies.keySet()) {
            System.out.println(IPOfCopies.get(key).toString().substring(1) + " connected at " + appCopies.get(key));
        }

        System.out.println("\n");
        if (uuid == null) {
            isAliveCounter++;
        }
        if (isAliveCounter == 10) {
            appCopies.remove(uuid);
            IPOfCopies.remove(uuid);
        }
    }
}
