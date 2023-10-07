package edu.hw1.task8;

record Position(int x, int y) {
    private static final int FIELD_SIZE = 8;

    /**
     * Check if this position does not go beyond the boundaries of the field
     */
    public boolean isValid() {
        return this.x >= 0 && this.x < FIELD_SIZE && this.y >= 0 && this.y < FIELD_SIZE;
    }
}
