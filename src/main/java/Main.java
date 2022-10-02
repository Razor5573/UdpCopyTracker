public class Main {
    public static void main(String[] args) {
        ThreadSender sender = new ThreadSender(args);
        ThreadReader reader = new ThreadReader(args);
        Thread realThreadSender = new Thread(sender);
        Thread realThreadReader = new Thread(reader);

        realThreadReader.start();
        realThreadSender.start();
    }
}
