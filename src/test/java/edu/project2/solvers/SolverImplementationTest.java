package edu.project2.solvers;

import edu.project2.StaticMaze;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base test class for Solver interface implementations
 */
public abstract class SolverImplementationTest<T extends Solver> {

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
    void testSolving() {
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
    }

    /**
     * Tests, that solver return empty list, when path doesn't exist
     */
    @Test
    void testPathNotFound() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);
        Maze maze = StaticMaze.getMazeWithUnreachableCells();

        List<Position> actualPath = solver.solve(maze, start, end);
        assertThat(actualPath).isEmpty();
    }

    @Test
    void testPathWhereStartEqualsEnd() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(0, 0);
        Maze maze = StaticMaze.getMaze();

        List<Position> actualPath = solver.solve(maze, start, end);

        assertThat(actualPath).hasSize(1);
        assertThat(actualPath).isEqualTo(new LinkedList<>(List.of(start)));
        assertThat(actualPath).isEqualTo(new LinkedList<>(List.of(new Position(start.x(), start.y()))));
    }

    @Test
    void testPathInMazeWithCycles() {
        Solver solver = instance;
        Position start = new Position(0, 0);
        Position end = new Position(2, 1);
        Maze maze = StaticMaze.getMazeWithCycles();

        List<Position> actualPath = solver.solve(maze, start, end);

        assertThat(actualPath).hasSizeGreaterThan(1);
    }
}
