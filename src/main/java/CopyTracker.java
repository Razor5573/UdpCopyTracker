import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CopyTracker {
    private static ConcurrentHashMap<String, Long> appCopies = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, InetAddress> IPOfCopies = new ConcurrentHashMap<>();
    private static String uuid = null;
    private static Long time = null;

    public static synchronized void trackCopy(DatagramPacket receivedDatagram) {
        String data = new String(receivedDatagram.getData()).trim();

        if (!(("I'm alive" + uuid).equals(data))) {
            uuid = data;
        } else {
            time = System.currentTimeMillis();
        }

        if (time != null && uuid != null) {
            appCopies.put(uuid, time);
            IPOfCopies.put(uuid, receivedDatagram.getAddress());
            for (String key : appCopies.keySet()) {
                System.out.println(IPOfCopies.get(key).toString().substring(1) + " with UUID \"" + key + "\" sent ping at " +
                        new SimpleDateFormat("HH:mm:ss").format(appCopies.get(key)));

            }
            System.out.println("\n");
        }

        for (Map.Entry<String, Long> appCopiesEntry : appCopies.entrySet()) {
            if (System.currentTimeMillis() - appCopiesEntry.getValue() > 10000) {
                appCopies.remove(appCopiesEntry.getKey());
                IPOfCopies.remove(appCopiesEntry.getKey());
                if (IPOfCopies.containsKey(appCopiesEntry.getKey()) && appCopies.containsKey(appCopiesEntry.getKey())) {
                    System.out.println(IPOfCopies.get(appCopiesEntry.getKey()).toString().substring(1)
                            + " with UUID \"" + appCopiesEntry.getKey() + "\" sent ping at " +
                            new SimpleDateFormat("HH:mm:ss").format(appCopies.get(appCopiesEntry.getKey())));
                }

            }
        }
    }
}