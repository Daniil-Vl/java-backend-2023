package edu.project4.transformation.nonlineartransformations;

import edu.project4.image.Point;
import edu.project4.transformation.Transformation;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double teta = Math.atan(x / y);

        return new Point(
            teta / Math.PI * Math.sin(Math.PI * r),
            teta / Math.PI * Math.cos(Math.PI * r)
        );
    }
}
