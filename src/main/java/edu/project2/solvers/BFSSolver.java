package edu.project2.solvers;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
        ArrayList<Position> notVisitedNeighbours = new ArrayList<>();
        Cell[][] grid = maze.getGrid();

        Position currPos;
        Cell currCell;
        Position nextPos;

        queue.addLast(start);

        while (!queue.isEmpty()) {
            currPos = queue.pollFirst();

            if (currPos.equals(end)) {
                return backtrackPath(parents, start, end);
            }

            currCell = grid[currPos.y()][currPos.x()];
            visited.add(currPos);

            // Get left neighbour
            nextPos = new Position(currPos.x() - 1, currPos.y());
            if (!currCell.hasLeftWall() && !visited.contains(nextPos)) {
                notVisitedNeighbours.add(nextPos);
            }

            // Get top neighbour
            nextPos = new Position(currPos.x(), currPos.y() - 1);
            if (!currCell.hasTopWall() && !visited.contains(nextPos)) {
                notVisitedNeighbours.add(nextPos);
            }

            // Get right neighbour
            nextPos = new Position(currPos.x() + 1, currPos.y());
            if (!currCell.hasRightWall() && !visited.contains(nextPos)) {
                notVisitedNeighbours.add(nextPos);
            }

            // Get bottom neighbour
            nextPos = new Position(currPos.x(), currPos.y() + 1);
            if (!currCell.hasBottomWall() && !visited.contains(nextPos)) {
                notVisitedNeighbours.add(nextPos);
            }

            for (Position neighbour : notVisitedNeighbours) {
                queue.addLast(neighbour);

                // Mark currPos as parent of neighbour
                parents.put(neighbour, currPos);
            }

            notVisitedNeighbours.clear();
        }

        return new LinkedList<>();
    }

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
