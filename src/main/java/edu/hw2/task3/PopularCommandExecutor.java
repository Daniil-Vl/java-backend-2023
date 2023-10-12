package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final static Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    /**
     * Try to execute command maxAttempts times
     *
     * @param command - command to execute
     */
    private void tryExecute(String command) {

        int attempts = 0;
        ConnectionException commandNotExecutedException = new ConnectionException();

        // Trying to execute the command until we achieve success or exceed the number of attempts
        while (attempts < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                attempts++;
                LOGGER.info("Attempt - " + attempts);
                connection.execute(command);
                return;
            } catch (ConnectionException exc) {
                LOGGER.info("Connection exception appeared");
                LOGGER.info(exc.toString());
            } catch (Exception exc) {
                commandNotExecutedException.initCause(exc.getCause());
                LOGGER.info(exc.toString());
            }
        }

        LOGGER.info("Command -  " + command + " wasn't executed");
        throw commandNotExecutedException;
    }
}
