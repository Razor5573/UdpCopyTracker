import static java.lang.Thread.currentThread;
public class ThreadRemover implements Runnable {
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            CopyRemover.removeCopy(CopyAdder.getAppCopies(), CopyAdder.getIPOfCopies());
        }
    }
}
