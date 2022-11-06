public class Main {
    public static void main(String[] args) {
        ThreadSender sender = new ThreadSender(args);
        ThreadReader reader = new ThreadReader(args);
        ThreadRemover remover = new ThreadRemover();

        Thread realThreadSender = new Thread(sender);
        Thread realThreadReader = new Thread(reader);
        Thread realThreadRemover = new Thread(remover);

        realThreadReader.start();
        realThreadSender.start();
        realThreadRemover.start();
    }
}
