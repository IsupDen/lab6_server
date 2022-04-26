package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.logging.Logger;

public class Deliver {

    private final Invoker invoker;
    private final AutoFieldsSetter fieldsSetter;
    final Logger logger = Logger.getLogger(Deliver.class.getCanonicalName());

    public Deliver(Invoker invoker, AutoFieldsSetter fieldsSetter) {
        this.invoker = invoker;
        this.fieldsSetter = fieldsSetter;
    }

    public void answer(Request command, DatagramSocket datagramSocket, SocketAddress socketAddress) throws IOException {
        Response answer = invoker.execute(fieldsSetter.setFields(command));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outObj = new ObjectOutputStream(byteArrayOutputStream);
        outObj.writeObject(answer);

        DatagramPacket packet = new DatagramPacket(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), socketAddress);
        datagramSocket.send(packet);
        logger.info("Server send an answer!");
    }
}
