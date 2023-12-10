package edu.project4.image;

/**
 * Record need for transformation
 */
public record Point(double x, double y) {

    public Point rotate(double angle) {
        return new Point(
            Math.cos(angle) * x - Math.sin(angle) * y,
            Math.sin(angle) * x + Math.cos(angle) * y
        );
    }
}
