package edu.project2.generators;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Generate maze, using backtracker algorithm
 */
public class RecursiveBacktrackedGenerator implements MazeGenerator {

    private static final Position[] POSSIBLE_NEIGHBOURS_POSITIONS = {
        new Position(-1, 0),
        new Position(1, 0),
        new Position(0, -1),
        new Position(0, 1)
    };

    private final Random randomizer = new Random();

    /**
     * Generate maze, using backtracker algorithm (depth-first search)
     *
     * @param height - height of maze
     * @param width  - width of maze
     * @return maze
     */
    @Override
    public Maze generate(int height, int width) {
        HashSet<Position> visitedNodes = new HashSet<>(width * height);

        // Stack for depth-first search algorithm
        ArrayDeque<Position> cellsStack = new ArrayDeque<>(width * height);

        Position currPosition;
        Position nextPosition;
        ArrayList<Position> neighbours = new ArrayList<>();

        // Initialize maze with cells
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell();
            }
        }

        // Add initial position to stack
        cellsStack.addLast(new Position(0, 0));

        while (!cellsStack.isEmpty()) {
            currPosition = cellsStack.pollLast();
            visitedNodes.add(currPosition);

            // 1) Get all non-visited neighbours
            for (Position move : POSSIBLE_NEIGHBOURS_POSITIONS) {
                nextPosition = new Position(
                    currPosition.x() + move.x(),
                    currPosition.y() + move.y()
                );

                // Add nextPosition, if it has valid coordinates and non-visited
                if (nextPosition.x() >= 0
                    && nextPosition.x() < width
                    && nextPosition.y() >= 0
                    && nextPosition.y() < height
                    && !visitedNodes.contains(nextPosition)) {
                    neighbours.add(nextPosition);
                }
            }

            // 2) Randomly select one of these neighbours, if it has any
            if (!neighbours.isEmpty()) {
                nextPosition = neighbours.get(randomizer.nextInt(0, neighbours.size()));

                // 3) Break wall to next cell
                if (nextPosition.x() < currPosition.x()) {
                    grid[currPosition.y()][currPosition.x()].destroyLeftWall();
                    grid[nextPosition.y()][nextPosition.x()].destroyRightWall();
                } else if (nextPosition.x() > currPosition.x()) {
                    grid[currPosition.y()][currPosition.x()].destroyRightWall();
                    grid[nextPosition.y()][nextPosition.x()].destroyLeftWall();
                } else if (nextPosition.y() < currPosition.y()) {
                    grid[currPosition.y()][currPosition.x()].destroyTopWall();
                    grid[nextPosition.y()][nextPosition.x()].destroyBottomWall();
                } else if (nextPosition.y() > currPosition.y()) {
                    grid[currPosition.y()][currPosition.x()].destroyBottomWall();
                    grid[nextPosition.y()][nextPosition.x()].destroyTopWall();
                }

                // Add current position to stack
                cellsStack.addLast(currPosition);

                // Add next position, to move to it on next step
                cellsStack.addLast(nextPosition);

                neighbours.clear();
            }
        }

        return new Maze(grid);
    }
}
