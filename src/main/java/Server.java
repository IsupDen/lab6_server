import collection.CollectionManager;
import file.FileWorker;
import util.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.regex.Pattern;

import static util.ConsoleWorker.println;
import static util.ConsoleWorker.print;
import static util.TextFormat.errText;


public class Server {
    public static void main(String[] args) throws IOException {

        try {
            LogManager.getLogManager().readConfiguration(Server.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }
        FileHandler fileHandler = new FileHandler("server.log");
        java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(Server.class.getName());
        logger.addHandler(fileHandler);

        try (Scanner scanner = new Scanner(System.in)) {
            DatagramSocket datagramSocket = getDatagramSocket(scanner);
            CollectionManager collectionManager = new CollectionManager();
            ConsoleWorker console = new ConsoleWorker(scanner);
            FileWorker fileWorker = new FileWorker(collectionManager, console);
            if (args.length != 0) {
                fileWorker.setPath(args[0]);
            }
            else {
                println(errText("No file passed by argument!"));
                System.exit(-1);
            }
            if (!fileWorker.getFromCSV()) return;
            AutoFieldsSetter fieldsSetter = new AutoFieldsSetter(collectionManager.getHighId());
            Invoker invoker = new Invoker(collectionManager);
            Deliver deliver = new Deliver(invoker, fieldsSetter);
            Receiver receiver = new Receiver(deliver, datagramSocket);
            Runtime.getRuntime().addShutdownHook(new Thread(new ExitSaver(fileWorker)));
            receiver.start();
        }

    }

    private static DatagramSocket getDatagramSocket(Scanner scanner) {
        while (true) {
            try {
                return new DatagramSocket(getPort(scanner));
            } catch (SocketException e) {
                println(errText("Socket could not bind to the specified local port!"));
            }
        }
    }

    private static int getPort(Scanner scanner) {
        String arg;
        Pattern remoteHostPortPattern = Pattern.compile("\\d{1,5}");
        try {
            do {
                print("Please, enter remote host port: ");

                arg = scanner.nextLine();


            } while (!(remoteHostPortPattern.matcher(arg).find() && (Integer.parseInt(arg.trim()) < 65536)));

            return Integer.parseInt(arg);
        } catch (NoSuchElementException e) {
            System.exit(0);
            return 0;
        }
    }
}
