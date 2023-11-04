package edu.project2.solvers;

import edu.project2.StaticMaze;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import edu.project2.rendering.ANSIRenderer;
import edu.project2.rendering.Renderer;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base test class for Solver interface implementations
 */
public abstract class SolverImplementationTest<T extends Solver> {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Renderer renderer = new ANSIRenderer();
    private T instance;

    protected abstract T createInstance();

    @BeforeEach
    public void setUp() {
        instance = createInstance();
    }

    /**
     * Test successful pathfinding procedure
     */
    @Test
    void findPathInPredefinedMaze() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);

        Maze maze = StaticMaze.getMaze();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedPath = List.of(
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(2, 2),
            new Position(2, 1),
            new Position(2, 0)
        );

        assertThat(actualPath).hasSizeGreaterThan(1);
        assertThat(actualPath).isEqualTo(expectedPath);

        LOGGER.info("findPathInPredefinedMaze for class " + instance.getClass());
        LOGGER.info("Maze: \n" + renderer.render(maze));
        LOGGER.info("Maze with expected path: \n" + renderer.render(maze, expectedPath));
        LOGGER.info("Maze with actual path: \n" + renderer.render(maze, actualPath));
    }

    /**
     * Tests, that solver return empty list, when path doesn't exist
     */
    @Test
    void findPathWhenItDoesNotExists() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);
        Maze maze = StaticMaze.getMazeWithUnreachableCells();

        List<Position> actualPath = solver.solve(maze, start, end);
        assertThat(actualPath).isEmpty();

        LOGGER.info("findPathWhenItDoesNotExists for class " + instance.getClass());
        LOGGER.info("Maze: \n" + renderer.render(maze));
        LOGGER.info("Maze with actual path: \n" + renderer.render(maze, actualPath));
    }

    @Test
    void findPathWhereStartEqualsEnd() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(0, 0);
        Maze maze = StaticMaze.getMaze();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedPath = List.of(start);

        assertThat(actualPath).hasSize(1);
        assertThat(actualPath).isEqualTo(expectedPath);

        LOGGER.info("findPathWhereStartEqualsEnd for class " + instance.getClass());
        LOGGER.info("Maze: \n" + renderer.render(maze));
        LOGGER.info("Maze with expected path: \n" + renderer.render(maze, expectedPath));
        LOGGER.info("Maze with actual path: \n" + renderer.render(maze, actualPath));
    }

    @Test
    void findPathInMazeWithCycles() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 1);
        Maze maze = StaticMaze.getMazeWithCycles();

        List<Position> actualPath = solver.solve(maze, start, end);

        assertThat(actualPath).hasSizeGreaterThan(1);

        LOGGER.info("findPathInMazeWithCycles for class " + instance.getClass());
        LOGGER.info("Maze: \n" + renderer.render(maze));
        LOGGER.info("Maze with actual path: \n" + renderer.render(maze, actualPath));
    }
}
