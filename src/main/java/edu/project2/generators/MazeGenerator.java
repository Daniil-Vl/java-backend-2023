package edu.project2.generators;

import edu.project2.maze.Maze;

public interface MazeGenerator {
    Maze generate(int height, int width);
}
