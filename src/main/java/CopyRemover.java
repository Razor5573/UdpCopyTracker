
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CopyRemover {
    public static synchronized void removeCopy(ConcurrentHashMap<String, Long> appCopies, ConcurrentHashMap<String, InetAddress> IPOfCopies) {
        for (Map.Entry<String, Long> appCopiesEntry : appCopies.entrySet()) {
            if (System.currentTimeMillis() - appCopiesEntry.getValue() > 10000) {
                appCopies.remove(appCopiesEntry.getKey());
                IPOfCopies.remove(appCopiesEntry.getKey());
                if (IPOfCopies.containsKey(appCopiesEntry.getKey()) && appCopies.containsKey(appCopiesEntry.getKey())) {
                    System.out.println(IPOfCopies.get(appCopiesEntry.getKey()).toString().substring(1) + " with UUID \""
                            + appCopiesEntry.getKey() + "\" sent ping at " +
                            new SimpleDateFormat("HH:mm:ss").format(appCopies.get(appCopiesEntry.getKey())));
                }
            }
        }
    }
}
