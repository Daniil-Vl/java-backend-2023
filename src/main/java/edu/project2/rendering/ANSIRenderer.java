package edu.project2.rendering;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.List;

/**
 * Render maze, using ansi escape codes
 */
public class ANSIRenderer implements Renderer {

    public static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String PASSAGE = "   ";
    private static final String WALL = ANSI_YELLOW_BACKGROUND + PASSAGE + ANSI_RESET;
    private static final String PATH_PASSAGE = ANSI_RED + " * " + ANSI_RESET;
    private static final String START_OR_END = ANSI_GREEN + " ! " + ANSI_RESET;

    /**
     * Render maze with empty path
     *
     * @param maze - maze to render
     * @return String - rendered maze
     */
    @Override public String render(Maze maze) {
        return this.render(maze, List.of());
    }

    /**
     * Render maze with given path
     *
     * @param maze - maze to render
     * @param path - path to render
     * @return String - rendered maze
     */
    @Override public String render(Maze maze, List<Position> path) throws IllegalArgumentException {
        maze.validatePath(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (String[] row : getGraphicGrid(maze, path)) {
            for (String elem : row) {
                stringBuilder.append(elem);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Get grid of strings for maze
     * Here each element is passage, path_passage or wall string representation
     *
     * @param maze - maze to render
     * @param path - path to render
     * @return String[][] - grid
     */
    private String[][] getGraphicGrid(Maze maze, List<Position> path) {

        int width = 2 * maze.getWidth() + 1;
        int height = 2 * maze.getHeight() + 1;
        String[][] resultGrid = new String[height][width];

        Cell[][] grid = maze.getGrid();

        int x = 1;
        int y = 1;

        for (Cell[] row : grid) {
            for (Cell currCell : row) {
                resultGrid[y][x] = switch (currCell.getType()) {
                    case PASSAGE -> PASSAGE;
                    case PATH_PASSAGE -> PATH_PASSAGE;
                };

                // Place walls and passage on the right and bottom side from current cell
                resultGrid[y][x + 1] = currCell.hasRightWall() ? WALL : PASSAGE;
                resultGrid[y + 1][x] = currCell.hasBottomWall() ? WALL : PASSAGE;
                resultGrid[y + 1][x + 1] = WALL;

                x += 2;
            }

            y += 2;
            x = 1;
        }

        // Add path on grid after rendering grid
        addPath(resultGrid, path);

        // Add top and bottom maze boundaries
        for (x = 0; x < width; x++) {
            resultGrid[0][x] = WALL;
            resultGrid[height - 1][x] = WALL;
        }

        // Add left and right maze boundaries
        for (y = 1; y < height - 1; y++) {
            resultGrid[y][0] = WALL;
            resultGrid[y][width - 1] = WALL;
        }

        return resultGrid;
    }

    /**
     * Return cell's position on graphic grid
     * It needed, because grid contain walls, which also took place on grid
     */
    private Position getPositionOnGraphicGrid(int x, int y) {
        return new Position(2 * x + 1, 2 * y + 1);
    }

    /**
     * Add path on already built maze grid
     *
     * @param grid - matrix, containing string representations of grid elements
     * @param path - path to add
     */
    private void addPath(String[][] grid, List<Position> path) {
        int x;
        int y;
        Position currPosition;
        Position nextPosition;

        // Render path, if there are only one cell
        if (path.size() == 1) {
            currPosition = path.get(0);
            x = getPositionOnGraphicGrid(currPosition.x(), currPosition.y()).x();
            y = getPositionOnGraphicGrid(currPosition.x(), currPosition.y()).y();
            grid[y][x] = START_OR_END;
        }

        for (int i = 0; i < path.size() - 1; i++) {
            currPosition = path.get(i);
            x = getPositionOnGraphicGrid(currPosition.x(), currPosition.y()).x();
            y = getPositionOnGraphicGrid(currPosition.x(), currPosition.y()).y();

            // If this is first
            grid[y][x] = i == 0 ? START_OR_END : PATH_PASSAGE;

            nextPosition = path.get(i + 1);

            // Render stars in passages to next cell on path
            if (nextPosition.x() < currPosition.x()) {
                grid[y][x - 1] = PATH_PASSAGE;
            } else if (nextPosition.x() > currPosition.x()) {
                grid[y][x + 1] = PATH_PASSAGE;
            } else if (nextPosition.y() < currPosition.y()) {
                grid[y - 1][x] = PATH_PASSAGE;
            } else if (nextPosition.y() > currPosition.y()) {
                grid[y + 1][x] = PATH_PASSAGE;
            } else {
                throw new IllegalArgumentException("Invalid path, two points have same x or y coordinate");
            }

            x = getPositionOnGraphicGrid(nextPosition.x(), nextPosition.y()).x();
            y = getPositionOnGraphicGrid(nextPosition.x(), nextPosition.y()).y();
            grid[y][x] = START_OR_END;
        }
    }

}
