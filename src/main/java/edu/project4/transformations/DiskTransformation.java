package edu.project4.transformations;

import edu.project4.image.Point;

public class DiskTransformation implements Transformation {
    // r = sqrt(x^2 + y^2)
    // teta = arctg(x/y)
    // phi = arcth(y/x)
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double teta = Math.atan(x / y);
        double phi = Math.atan(y / x);

        return new Point(
            teta / Math.PI * Math.sin(Math.PI * r),
            teta / Math.PI * Math.cos(Math.PI * r)
        );
    }
}
