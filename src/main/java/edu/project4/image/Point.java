package edu.project4.image;

/**
 * Record need for transformation
 */
public record Point(double x, double y) {

    public static Point rotate(Point point, double angle) {
        return new Point(
            Math.cos(angle) * point.x() - Math.sin(angle) * point.y(),
            Math.sin(angle) * point.x() + Math.cos(angle) * point.y()
        );
    }
}
