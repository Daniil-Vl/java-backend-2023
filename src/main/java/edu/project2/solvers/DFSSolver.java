package edu.project2.solvers;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Find path between two points in maze using depth-first search algorithm
 */
public class DFSSolver implements Solver {
    Set<Position> visitedNodes;

    /**
     * Find path between two points, using dfs
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in path
     */
    @Override
    public List<Position> solve(Maze maze, Position start, Position end) {
        this.visitedNodes = new HashSet<>();
        Cell[][] grid = maze.getGrid();

        // Return empty path for empty maze
        if (grid.length < 1) {
            return List.of();
        }

        return this.solve(maze, start, end, new LinkedList<>());
    }

    @SuppressWarnings("MagicNumber")
    private List<Position> solve(Maze maze, Position start, Position end, LinkedList<Position> path) {
        path.add(start);
        visitedNodes.add(start);

        // If we found destination point, then return completed path
        if (start.equals(end)) {
            return path;
        }

        List<Position> pathFromNeighbour;
        List<Position> notVisitedNeighbours =
            maze.getNeighbours(start).stream().filter(el -> !this.visitedNodes.contains(el)).toList();

        // Try to find path using each neighbour cell
        for (Position neighbour : notVisitedNeighbours) {
            pathFromNeighbour = solve(maze, neighbour, end, new LinkedList<>(path));

            // Return path, if it contains end point
            if (!pathFromNeighbour.isEmpty() && pathFromNeighbour.getLast().equals(end)) {
                return pathFromNeighbour;
            }
        }

        // If not found path to end point in above cycle, then path doesn't exist
        // In this case we return empty path
        return new LinkedList<>();
    }
}
