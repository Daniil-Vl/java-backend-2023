package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConnectionTest {

    /**
     * Return list of commandExecutors with different connection managers, but with maxAttempts = 0
     *
     * @return - list of commandExecutors
     */
    static Arguments[] commandExecutors() {
        return new Arguments[] {
            Arguments.of(new PopularCommandExecutor(
                new DefaultConnectionManager(),
                0
            )),
            Arguments.of(new PopularCommandExecutor(
                new FaultyConnectionManager(),
                0
            )),
        };
    }

    @ParameterizedTest
    @MethodSource("commandExecutors")
    void testUpdatePackages(PopularCommandExecutor commandExecutor) {
        assertThrows(ConnectionException.class, commandExecutor::updatePackages);
    }
}
