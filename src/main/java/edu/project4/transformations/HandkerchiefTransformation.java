package edu.project4.transformations;

import edu.project4.image.Point;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double teta = Math.atan(x / y);
        return new Point(
            r * Math.sin(teta + r),
            r * Math.cos(teta - r)
        );
    }
}
