import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

public class CopyAdder {
    private static ConcurrentHashMap<String, Long> appCopies = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, InetAddress> IPOfCopies = new ConcurrentHashMap<>();
    private static String uuid = null;
    private static Long time = null;

    public static synchronized void addCopy(DatagramPacket receivedDatagram) {
        String data = new String(receivedDatagram.getData()).trim();

        if (!(("I'm alive" + uuid).equals(data))) {
            uuid = data;
        } else {
            time = System.currentTimeMillis();
        }

        if (time != null && uuid != null) {
            appCopies.put(uuid, time);
            IPOfCopies.put(uuid, receivedDatagram.getAddress());
            if(uuid.equals(data)){
                for (String key : appCopies.keySet()) {
                    System.out.println(IPOfCopies.get(key).toString().substring(1) + " with UUID \"" + key + "\" sent ping at " +
                            new SimpleDateFormat("HH:mm:ss").format(appCopies.get(key)));
                }
                System.out.println("\n");
            }

        }
    }

    public static ConcurrentHashMap<String, InetAddress> getIPOfCopies() {
        return IPOfCopies;
    }

    public static ConcurrentHashMap<String, Long> getAppCopies() {
        return appCopies;
    }
}