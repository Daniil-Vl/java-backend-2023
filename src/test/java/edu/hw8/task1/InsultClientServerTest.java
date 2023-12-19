package edu.hw8.task1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsultClientServerTest {
    private static final int PORT = 4048;
    private static final String HOST = "127.0.0.1";

    private static final List<String> COMMANDS = List.of(
        "личности",
        "оскорбления",
        "глупый",
        "unsupported theme/command",
        "интеллект"
    );

    @Test
    void multipleClients() throws Exception {
        // 1) Start server
        InsultServer server = new InsultServer(PORT);
        Thread serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();

        // 2) Start and execute commands for 8 clients
        int numberOfClients = 8;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfClients);
        List<Callable<List<String>>> clients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            clients.add(new InsultClient(HOST, PORT, COMMANDS));
        }
        List<Future<List<String>>> listOfFutureClientsResponses = executorService.invokeAll(clients);

        for (Future<List<String>> futureClientResponses : listOfFutureClientsResponses) {
            List<String> actualResponses = futureClientResponses.get();
            assertClientCollectedResponses(actualResponses, COMMANDS);
        }

        server.close();
    }

    @Test
    void singleClient() throws Exception {
        // 1) Start server
        InsultServer server = new InsultServer(PORT);
        Thread serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();

        // 2) Start & execute client
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<String>> futureResponses = executorService.submit(new InsultClient(HOST, PORT, COMMANDS));
        List<String> actualResponses = futureResponses.get();

        // 3) Compare actual and expected responses
        assertClientCollectedResponses(actualResponses, COMMANDS);

        server.close();
    }

    void assertClientCollectedResponses(List<String> responses, List<String> commands) {
        assertThat(responses).hasSize(commands.size());

        for (int i = 0; i < commands.size(); i++) {
            String request = COMMANDS.get(i);
            try {
                List<String> listOfExpectedResponses = InsultDictionary.getListOfInsultsByTheme(request);
                assertThat(responses.get(i))
                    .isIn(listOfExpectedResponses);
            } catch (IllegalArgumentException exc) {
                assertThat(responses.get(i)).isEqualTo("Unsupported theme, please try another");
            }
        }
    }
}
