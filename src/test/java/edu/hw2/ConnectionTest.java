package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.StableConnectionManager;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConnectionTest {
    static Stream<Arguments> commandExecutorsWithoutAttempts() {
        return Stream.of(
            Arguments.of(new PopularCommandExecutor(
                new DefaultConnectionManager(),
                0
            )),
            Arguments.of(new PopularCommandExecutor(
                new FaultyConnectionManager(),
                0
            ))
        );
    }

    /**
     * Test, that command will not be executed, if set maxAttempts = 0
     *
     * @param commandExecutor command executors to test on
     */
    @ParameterizedTest
    @MethodSource("commandExecutorsWithoutAttempts")
    void testUpdatePackages(PopularCommandExecutor commandExecutor) {
        assertThrows(ConnectionException.class, commandExecutor::updatePackages);
    }

    /**
     * Test command executor with stable connection
     */
    @Test
    void testSuccessExecution() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(new StableConnectionManager(), 10);
        assertDoesNotThrow(commandExecutor::makeMoney);
        assertDoesNotThrow(commandExecutor::updatePackages);
    }

    /**
     * Test that command will not be executed with FaultyConnection, always throwing ConnectionException
     */
    @Test
    void testFailedExecution() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(
            new FaultyConnectionManager(1),
            10
        );

        assertThrows(ConnectionException.class, commandExecutor::makeMoney);
        assertThrows(ConnectionException.class, commandExecutor::updatePackages);
    }
}
