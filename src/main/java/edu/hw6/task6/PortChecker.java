package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortChecker {

    private static final int FIRST_PORT = 1;
    private static final int LAST_PORT = 5001;
    private static final String LOCALHOST = "localhost";
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Applications, using this ports with tcp and udp
     */
    private static final HashMap<Integer, String> PORT_APPS = new HashMap<>(Map.of(
        22, "SSH",
        23, "TELNET",
        25, "SMTP",
        53, "DNS",
        80, "HTTP",
        123, "NTP",
        143, "IMAP",
        443, "HTTPS"
    ));

    private PortChecker() {
    }

    public static void checkPorts() throws UnknownHostException {
        LOGGER.info("Протокол  Порт   Сервис");
        int count = 0;

        for (int portNumber = FIRST_PORT; portNumber <= LAST_PORT; portNumber++) {

            // TCP connections
            try (Socket client = new Socket(InetAddress.getByName(LOCALHOST), portNumber)) {
                count++;
            } catch (IOException ignored) {
                if (PORT_APPS.containsKey(portNumber)) {
                    LOGGER.info("TCP       %-6d %s".formatted(portNumber, PORT_APPS.get(portNumber)));
                } else {
                    LOGGER.info("TCP       %-6d N/A".formatted(portNumber));
                }
            }

            // UDP connections
            try (DatagramSocket client = new DatagramSocket(portNumber, InetAddress.getByName(LOCALHOST))) {
                count++;
            } catch (SocketException ignored) {
                if (PORT_APPS.containsKey(portNumber)) {
                    LOGGER.info("UDP       %-6d %s".formatted(portNumber, PORT_APPS.get(portNumber)));
                } else {
                    LOGGER.info("UDP       %-6d N/A".formatted(portNumber));
                }
            }
        }
    }
}
