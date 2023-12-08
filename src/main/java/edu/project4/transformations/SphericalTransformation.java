package edu.project4.transformations;

import edu.project4.image.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        return new Point(
            point.x() / (r * r),
            point.y() / (r * r)
        );
    }
}
