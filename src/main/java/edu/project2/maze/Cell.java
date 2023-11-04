package edu.project2.maze;

public class Cell {
    private final int row;
    private final int col;
    private final CellType type;
    private boolean leftWall = true;
    private boolean rightWall = true;
    private boolean topWall = true;
    private boolean bottomWall = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.type = CellType.PASSAGE;
    }

    public Cell(int row, int col, boolean left, boolean top, boolean right, boolean bottom) {
        this(row, col);
        this.leftWall = left;
        this.topWall = top;
        this.rightWall = right;
        this.bottomWall = bottom;
    }

    public boolean hasLeftWall() {
        return leftWall;
    }

    public void destroyLeftWall() {
        this.leftWall = false;
    }

    public boolean hasRightWall() {
        return rightWall;
    }

    public void destroyRightWall() {
        this.rightWall = false;
    }

    public boolean hasTopWall() {
        return topWall;
    }

    public void destroyTopWall() {
        this.topWall = false;
    }

    public boolean hasBottomWall() {
        return bottomWall;
    }

    public void destroyBottomWall() {
        this.bottomWall = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellType getType() {
        return type;
    }
}
