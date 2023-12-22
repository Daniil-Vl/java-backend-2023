package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsultClient implements Callable<List<String>> {
    private static final Logger LOGGER = LogManager.getLogger();

    private final String host;
    private final int port;
    private final List<String> commands;
    private final List<String> responses = new ArrayList<>();

    public InsultClient(String host, int port, List<String> commandToExecute) {
        this.host = host;
        this.port = port;
        this.commands = new ArrayList<>(commandToExecute);
    }

    @Override
    public List<String> call() throws Exception {

        this.responses.clear();

        try (
            Socket socket = new Socket(host, port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                true
            )
        ) {
            for (String message : commands) {
                LOGGER.info("Sending request - {}", message);
                printWriter.println(message);
                String response = bufferedReader.readLine();
                this.responses.add(response);
                LOGGER.info("Getting response - {}", response);
            }
        }

        return this.responses;
    }

//    @Override
//    public void run() {
//        try (
//            Socket socket = new Socket(HOST, PORT);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter printWriter = new PrintWriter(
//                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
//                true
//            )
//        ) {
//            for (String message : commands) {
//                LOGGER.info("Sending request - {}", message);
//                printWriter.println(message);
//                String reply = bufferedReader.readLine();
//                LOGGER.info("Getting reply - {}", reply);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
