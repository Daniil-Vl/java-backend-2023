package edu.project2.rendering;

import edu.project2.StaticMaze;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ANSIRendererTest {
    public static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String PASSAGE = "   ";
    private static final String WALL = ANSI_YELLOW_BACKGROUND + PASSAGE + ANSI_RESET;
    private static final String PATH_PASSAGE = ANSI_RED + " * " + ANSI_RESET;
    private static final String START_OR_END = ANSI_GREEN + " ! " + ANSI_RESET;

    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    void testRenderOnlyMaze() {
        Renderer renderer = new ANSIRenderer();
        Maze maze = StaticMaze.getMaze();

        // Maze 3x3 have 7 wall block on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
            + WALL + PASSAGE.repeat(3) + WALL + PASSAGE + WALL + "\n"
            + WALL.repeat(3) + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
            + WALL.repeat(7) + "\n";

        String actual = renderer.render(maze);

        LOGGER.info("testRenderOnlyMaze");
        LOGGER.info("Expected maze rendering\n" + expectedRenderResult);
        LOGGER.info("Actual maze rendering\n" + actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderWithPath() {
        Renderer renderer = new ANSIRenderer();
        Maze maze = StaticMaze.getMaze();
        List<Position> path = List.of(
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(2, 2),
            new Position(2, 1),
            new Position(2, 0)
        );

        // Maze 3x3 have 7 wall block on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
            + WALL + START_OR_END + PATH_PASSAGE.repeat(2) + WALL + START_OR_END + WALL + "\n"
            + WALL.repeat(3) + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PATH_PASSAGE + WALL + PATH_PASSAGE + WALL + "\n"
            + WALL + PASSAGE + PASSAGE + PATH_PASSAGE + PATH_PASSAGE + PATH_PASSAGE + WALL + "\n"
            + WALL.repeat(7) + "\n";

        String actual = renderer.render(maze, path);

        LOGGER.info("testRenderWithPath");
        LOGGER.info("Expected maze rendering\n" + expectedRenderResult);
        LOGGER.info("Actual maze rendering\n" + actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderPathWithOneCell() {
        Renderer renderer = new ANSIRenderer();
        Maze maze = StaticMaze.getMaze();
        List<Position> path = List.of(
            new Position(0, 0)
        );

        // Maze 3x3 have 7 wall block on the boundaries
        String expectedRenderResult = WALL.repeat(7) + "\n"
            + WALL + START_OR_END + PASSAGE.repeat(2) + WALL + PASSAGE + WALL + "\n"
            + WALL.repeat(3) + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
            + WALL.repeat(7) + "\n";

        String actual = renderer.render(maze, path);

        LOGGER.info("testRenderPathWithOneCell");
        LOGGER.info("Expected maze rendering\n" + expectedRenderResult);
        LOGGER.info("Actual maze rendering\n" + actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderMazeWithUnreachableCells() {
        Renderer renderer = new ANSIRenderer();
        Maze maze = StaticMaze.getMazeWithUnreachableCells();
        String expectedRenderResult = WALL.repeat(7) + "\n"
            + WALL + PASSAGE.repeat(3) + WALL + PASSAGE + WALL + "\n"
            + WALL.repeat(3) + PASSAGE + WALL + WALL + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + WALL + PASSAGE + WALL + PASSAGE + WALL + "\n"
            + WALL + PASSAGE + PASSAGE + PASSAGE + PASSAGE + PASSAGE + WALL + "\n"
            + WALL.repeat(7) + "\n";

        String actual = renderer.render(maze);

        LOGGER.info("testRenderMazeWithUnreachableCells");
        LOGGER.info("Expected maze rendering\n" + expectedRenderResult);
        LOGGER.info("Actual maze rendering\n" + actual);

        assertThat(actual).isEqualTo(expectedRenderResult);
    }

    @Test
    void testRenderingInvalidMaze() {
        Renderer renderer = new ANSIRenderer();
        Maze maze = StaticMaze.getMazeWithUnreachableCells();
        List<Position> path = List.of(
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
            new Position(2, 2),
            new Position(2, 1),
            new Position(2, 0)
        );

        LOGGER.info("testRenderingInvalidMaze");
        LOGGER.info("Maze\n" + renderer.render(maze));
        LOGGER.info("Path: " + path);

        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze, path));
    }
}
