package util;

import file.FileWorker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import static util.ConsoleWorker.println;
import static util.TextFormat.successText;


public class Receiver {

    private final Deliver deliver;
    private final DatagramSocket datagramSocket;
    final Logger logger = Logger.getLogger(FileWorker.class.getCanonicalName());

    public  Receiver(Deliver deliver, DatagramSocket datagramSocket) {
        this.deliver = deliver;
        this.datagramSocket = datagramSocket;
    }

    public void start() {
        println(getInformation());
        while (true) {
            try {
                byte[] buf = new byte[32000];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                ObjectInputStream inObj = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                Request request = (Request) inObj.readObject();
                deliver.answer(request, datagramSocket, packet.getSocketAddress());
            } catch (IOException e) {
                logger.severe("Some problems with network!");
            } catch (ClassNotFoundException e) {
                logger.severe("Client sent outdated request!");
            }
        }
    }

    public String getInformation() {
        try {
            return successText("\nSERVER STATUS:\n") +
                    successText("--------------------------------------------------\n") +
                    "Server address: " + successText(String.valueOf(InetAddress.getLocalHost())) + "\n" +
                    "Server port:    " + successText(String.valueOf(datagramSocket.getLocalPort())) + "\n" +
                    successText("--------------------------------------------------\n");
        } catch (UnknownHostException ignored) {return null;}
    }
}
