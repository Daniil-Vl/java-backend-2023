package edu.project2.solvers;

import edu.project2.StaticMaze;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BFSSolverTest extends SolverImplementationTest<BFSSolver> {

    @Override
    protected BFSSolver createInstance() {
        return new BFSSolver();
    }

    /**
     *
     */
    @Override
    void testPathInMazeWithCycles() {
        Solver solver = new BFSSolver();
        Position start = new Position(0, 0);
        Position end = new Position(2, 1);
        Maze maze = StaticMaze.getMazeWithCycles();

        List<Position> actualPath = solver.solve(maze, start, end);
        List<Position> expectedShortestPath = List.of(
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(2, 1)
        );

        assertThat(actualPath).hasSizeGreaterThan(1);
        assertThat(actualPath).isEqualTo(expectedShortestPath);
    }
}
