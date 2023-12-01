package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsultServer implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExecutorService executorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors());
    private final ServerSocket serverSocket;

    public InsultServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException, InterruptedException {
        try {
            LOGGER.info("Server listening on port: {}", this.serverSocket.getLocalPort());
            while (!this.serverSocket.isClosed()) {
                executorService.submit(
                    new ClientRequestHandler(serverSocket.accept())
                );
                LOGGER.info("Start executing task with new connection");
            }
        } catch (SocketException exc) {
            LOGGER.warn("Server stopped by exception");
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Closing server...");
        this.serverSocket.close();
    }
}
