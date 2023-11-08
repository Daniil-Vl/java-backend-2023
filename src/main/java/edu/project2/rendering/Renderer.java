package edu.project2.rendering;

import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.List;

public interface Renderer {

    /**
     * Render maze without path
     *
     * @param maze - maze to render
     * @return String - rendered maze
     */
    String render(Maze maze);

    /**
     * Render maze with path on it
     *
     * @param maze - maze to render
     * @param path - path to render
     * @return String - rendered maze
     */
    String render(Maze maze, List<Position> path);
}
