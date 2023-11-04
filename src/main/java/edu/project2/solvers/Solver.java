package edu.project2.solvers;

import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.List;

public interface Solver {
    /**
     * Find path between two points
     * If path doesn't exist returns empty list
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in path
     */
    List<Position> solve(Maze maze, Position start, Position end);
}
