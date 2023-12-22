package edu.hw8.task1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("ThrowFromFinallyBlock") public class ClientRequestHandler implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Timeout for 60 seconds
     */
    private final static int TIMEOUT = 60000;
    private final Socket user;

    public ClientRequestHandler(Socket userSocket) throws SocketException {
        user = userSocket;
        user.setSoTimeout(TIMEOUT);
    }

    @Override
    public void run() {
        LOGGER.info("Start handling user connection {}", user.getInetAddress());
        try (
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(user.getInputStream()));
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(user.getOutputStream()), true)
        ) {

            String theme = bufferedReader.readLine();
            while (theme != null) {
                LOGGER.info("Getting request - {}", theme);
                try {
                    String response = InsultDictionary.getInsultByTheme(theme);
                    writer.println(response);
                    LOGGER.info("The sent response - {}", response);
                } catch (IllegalArgumentException exc) {
                    writer.println("Unsupported theme, please try another");
                }
                theme = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                user.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("Stop handling user connection");
        }
    }
}
