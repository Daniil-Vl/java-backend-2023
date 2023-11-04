package edu.project2;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import org.jetbrains.annotations.NotNull;

public class StaticMaze {
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
                new Cell(0, 0, true, true, false, true),
                new Cell(0, 1, false, true, true, false),
                new Cell(0, 2, true, true, true, false)
            },
            {
                new Cell(1, 0, true, true, true, false),
                new Cell(1, 1, true, false, true, false),
                new Cell(1, 2, true, false, true, false),
            },
            {
                new Cell(2, 0, true, false, false, true),
                new Cell(2, 1, false, false, false, true),
                new Cell(2, 2, false, false, true, true),
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
                new Cell(0, 0, true, true, false, true),
                new Cell(0, 1, false, true, true, false),
                new Cell(0, 2, true, true, true, true)
            },
            {
                new Cell(1, 0, true, true, true, false),
                new Cell(1, 1, true, false, true, false),
                new Cell(1, 2, true, true, true, false),
            },
            {
                new Cell(2, 0, true, false, false, true),
                new Cell(2, 1, false, false, false, true),
                new Cell(2, 2, true, false, true, true),
            }
        };
        return new Maze(grid);
    }
}
