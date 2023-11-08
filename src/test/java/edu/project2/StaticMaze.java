package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.rendering.ANSIRenderer;
import edu.project2.rendering.Renderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class StaticMaze {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static Renderer renderer = new ANSIRenderer();

    /**
     * Maze like this
     * ##########
     * #     #  #
     * ####  #  #
     * #  #  #  #
     * #        #
     * ##########
     */
    @NotNull public static Maze getMaze() {
        Cell[][] grid = new Cell[][] {
            {
                new Cell(true, true, false, true),
                new Cell(false, true, true, false),
                new Cell(true, true, true, false)
            },
            {
                new Cell(true, true, true, false),
                new Cell(true, false, true, false),
                new Cell(true, false, true, false),
            },
            {
                new Cell(true, false, false, true),
                new Cell(false, false, false, true),
                new Cell(false, false, true, true),
            }
        };
        return new Maze(grid);
    }

    /**
     * Maze like this
     * ##########
     * #     #  #
     * ####  ####
     * #  #  #  #
     * #        #
     * ##########
     */
    @NotNull public static Maze getMazeWithUnreachableCells() {
        Cell[][] grid = new Cell[][] {
            {
                new Cell(true, true, false, true),
                new Cell(false, true, true, false),
                new Cell(true, true, true, true)
            },
            {
                new Cell(true, true, true, false),
                new Cell(true, false, true, false),
                new Cell(true, true, true, false),
            },
            {
                new Cell(true, false, false, true),
                new Cell(false, false, false, true),
                new Cell(true, false, true, true),
            }
        };
        return new Maze(grid);
    }

    @NotNull public static Maze getMazeWithCycles() {
        Cell[][] grid = new Cell[][] {
            {
                new Cell(true, true, false, false),
                new Cell(false, true, false, false),
                new Cell(false, true, true, true),
            },
            {
                new Cell(true, false, true, false),
                new Cell(true, false, false, true),
                new Cell(false, true, true, false),
            },
            {
                new Cell(true, false, false, true),
                new Cell(false, true, false, true),
                new Cell(false, false, true, true),
            },
        };

        return new Maze(grid);
    }

    private static void renderMaze() {
        LOGGER.info("Maze: \n" + renderer.render(getMaze()));
    }

    private static void renderMazeWithUnreachableCells() {
        LOGGER.info("Maze with unreachable cells: \n" + renderer.render(getMazeWithUnreachableCells()));
    }

    private static void renderMazeWithCycles() {
        LOGGER.info("Maze with cycles: \n" + renderer.render(getMazeWithCycles()));
    }

    public static void main(String[] args) {
        renderMaze();
        renderMazeWithUnreachableCells();
        renderMazeWithCycles();
    }
}
