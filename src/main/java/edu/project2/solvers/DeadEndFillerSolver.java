package edu.project2.solvers;

import edu.project2.maze.Cell;
import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Find path between two points, using Dead end filler algorithm
 */
public class DeadEndFillerSolver implements Solver {

    /**
     * Find path between two points
     * If path doesn't exist returns empty list
     * This is Dead end filler algorithm implementation
     *
     * @param maze  - maze
     * @param start - starting point
     * @param end   - destination point
     * @return ordered sequence of points in path
     */
    @Override
    public List<Position> solve(Maze maze, Position start, Position end) {
        if (start.equals(end)) {
            return new LinkedList<>(List.of(start));
        }

        Cell[][] grid = maze.getGrid();
        HashSet<Position> deadEnds = new HashSet<>();
        Position currPos;
        Cell currCell;

        // 1) Find all dead ends (cells, which have only one pass), except start and end cells
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                currPos = new Position(x, y);

                if (currPos.equals(start) || currPos.equals(end)) {
                    continue;
                }

                currCell = grid[y][x];
                if (currCell.countPasses() <= 1) {
                    deadEnds.add(currPos);
                }
            }
        }

        // 2) Fill all paths to the dead ends
        HashSet<Position> filled = new HashSet<>();
        List<Position> unfilledNeighbours;
        for (Position deadEndPos : deadEnds) {
            currPos = deadEndPos;
            while (true) {
                unfilledNeighbours = getUnfilledNeighbours(currPos, maze, filled);

                // If current cell have only one reachable unfilled neighbour,
                // then it is passage to the dead end, and it should be filled
                // Otherwise, we have reached fork cell, and here we can stop cycle
                if (unfilledNeighbours.size() == 1) {
                    filled.add(currPos);
                    currPos = unfilledNeighbours.get(0);
                } else {
                    break;
                }
            }
        }

        // 3) Collect path from unfilled cells
        LinkedList<Position> path = new LinkedList<>();
        HashSet<Position> visited = new HashSet<>();
        currPos = start;
        path.add(start);
        visited.add(start);
        while (!currPos.equals(end)) {

            unfilledNeighbours = getUnfilledNeighbours(currPos, maze, filled);

            // Return empty list, if path doesn't exist
            if (unfilledNeighbours.isEmpty()) {
                return new LinkedList<>();
            }

            // Get reachable unfilled and unvisited neighbour
            currPos = unfilledNeighbours.stream()
                .filter(el -> !visited.contains(el)).findFirst().orElseThrow();

            path.add(currPos);
            visited.add(currPos);
        }

        return path;
    }

    /**
     * Returns a reachable and unfilled neighbor, if such exist, otherwise returns empty list
     */
    private List<Position> getUnfilledNeighbours(Position pos, Maze maze, Set<Position> filled) {
        return maze.getNeighbours(pos).stream().filter(el -> !filled.contains(el)).toList();
    }
}
