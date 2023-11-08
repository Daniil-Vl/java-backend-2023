package edu.project2.solvers;

import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Find path between two points, using breadth-first search algorithm
 */
public class BFSSolver implements Solver {

    /**
     * Find path between two points
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in path
     */
    @Override
    public List<Position> solve(Maze maze, Position start, Position end) {
        ArrayDeque<Position> queue = new ArrayDeque<>();
        HashMap<Position, Position> parents = new HashMap<>();
        HashSet<Position> visited = new HashSet<>();
        List<Position> notVisitedNeighbours;
        Position currPos;

        queue.addLast(start);
        while (!queue.isEmpty()) {
            currPos = queue.pollFirst();

            // If we reached end position, then we can start collecting path
            if (currPos.equals(end)) {
                return backtrackPath(parents, start, end);
            }

            visited.add(currPos);

            // Get all unvisited neighbours
            notVisitedNeighbours = maze.getNeighbours(currPos).stream().filter(el -> !visited.contains(el)).toList();

            // Add them to queue an give them parent
            for (Position neighbour : notVisitedNeighbours) {
                queue.addLast(neighbour);
                parents.put(neighbour, currPos);
            }
        }

        return new LinkedList<>();
    }

    /**
     * Collect path from parents mapping
     *
     * @param parents - parents mapping
     * @param start   - start of path
     * @param end     - end of path
     * @return path
     */
    private List<Position> backtrackPath(Map<Position, Position> parents, Position start, Position end) {
        LinkedList<Position> path = new LinkedList<>(List.of(end));
        Position currPos = end;

        while (!currPos.equals(start)) {
            currPos = parents.get(currPos);
            path.addFirst(currPos);
        }

        return path;
    }
}
