package edu.project4.colors;

public record Color(int red, int green, int blue) {
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color PURPLE = new Color(158, 103, 210);
    public static final Color ORANGE = new Color(227, 101, 29);
}

