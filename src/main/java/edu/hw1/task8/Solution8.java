package edu.hw1.task8;

public class Solution8 {

    private static final int FIELD_SIZE = 8;
    private static final int[][] POSSIBLE_MOVES = {
        {-1, 2},
        {-1, -2},
        {1, 2},
        {1, -2},

        {-2, -1},
        {2, -1},
        {-2, 1},
        {2, 1},
    };

    public Solution8() {
    }

    /**
     * Check if current knight can defeat some another by one move
     *
     * @param field          - initial field
     * @param knightPosition - position of current knight
     * @return true, if knight can defeat, otherwise false
     */
    private boolean canDefeatKnight(int[][] field, Position knightPosition) {
        Position nextPos;
        for (int[] move : POSSIBLE_MOVES) {
            nextPos = new Position(knightPosition.x() + move[0], knightPosition.y() + move[1]);
            if (nextPos.isValid() && field[nextPos.y()][nextPos.x()] == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if some knight can defeat another knight on this field
     *
     * @param field - field
     * @return false, if some knight can defeat another, otherwise true
     */
    public boolean knightBoardCapture(int[][] field) {
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {

                // Found new knight
                if (field[y][x] == 1) {
                    Position knight = new Position(x, y);

                    // Return false, if during move meet another knight
                    if (canDefeatKnight(field, knight)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
