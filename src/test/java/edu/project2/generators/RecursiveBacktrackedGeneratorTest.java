package edu.project2.generators;

import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import edu.project2.rendering.ANSIRenderer;
import edu.project2.rendering.Renderer;
import edu.project2.solvers.DFSSolver;
import edu.project2.solvers.Solver;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RecursiveBacktrackedGeneratorTest {

    private static final MazeGenerator mazeGenerator = new RecursiveBacktrackedGenerator();
    private static final Solver solver = new DFSSolver();
    private static final Renderer renderer = new ANSIRenderer();
    private final static Logger LOGGER = LogManager.getLogger();

    // This test method relies on DFSSolver implementation
    @Test
    void generatedMazeDoesNotHaveUnreachableZones() {
        int height = 5;
        int width = 5;
        Maze maze = mazeGenerator.generate(height, width);
        List<Position> path;
        Position start = new Position(0, 0);
        Position end;

        LOGGER.info("generatedMazeDoesNotHaveUnreachableZones\n");
        LOGGER.info("Maze\n" + renderer.render(maze));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                end = new Position(x, y);
                path = solver.solve(maze, start, end);

                LOGGER.info(
                    "Path from (%d, %d) to (%d, %d)\n".formatted(start.x(), start.y(), end.x(), end.y())
                        + renderer.render(maze, path)
                );

                assertThat(path).hasSizeGreaterThan(0);
                assertThat(path.getLast()).isNotNull();
                assertThat(path.getLast()).isEqualTo(end);
            }
        }
    }
}
