package edu.hw9.task3;

import edu.project2.maze.Maze;
import edu.project2.maze.Position;
import edu.project2.solvers.Solver;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultiThreadedDFSSolver implements Solver {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private Set<Position> visitedNodes;

    @Override
    public List<Position> solve(Maze maze, Position start, Position end) {
        visitedNodes = new HashSet<>();
        RecursiveTask<List<Position>> task = new SolvingTask(maze, start, end);
        return this.forkJoinPool.invoke(task);
    }

    private class SolvingTask extends RecursiveTask<List<Position>> {
        private final Maze maze;
        private final Position start;
        private final Position end;

        private SolvingTask(Maze maze, Position start, Position end) {
            this.maze = maze;
            this.start = start;
            this.end = end;
        }

        @Override
        protected List<Position> compute() {
            List<Position> result = new ArrayList<>(List.of(start));
            visitedNodes.add(start);

            if (start.equals(end)) {
                return result;
            }

            List<Position> neighbours = maze.getNeighbours(start);
            List<RecursiveTask<List<Position>>> tasks = new ArrayList<>();

            for (Position neighbour : neighbours) {
                if (!visitedNodes.contains(neighbour)) {
                    SolvingTask task = new SolvingTask(maze, neighbour, end);
                    task.fork();
                    tasks.add(task);
                }
            }

            for (var task : tasks) {
                List<Position> pathFromNeighbour = task.join();

                if (!pathFromNeighbour.isEmpty() && pathFromNeighbour.getLast().equals(end)) {
                    result.addAll(pathFromNeighbour);
                    return result;
                }
            }

            return new ArrayList<>();
        }
    }
}
