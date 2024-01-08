package edu.project4.transformation.nonlineartransformations;

import edu.project4.image.Point;
import edu.project4.transformation.Transformation;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double teta = Math.atan(x / y);

        return new Point(
            r * Math.sin(teta * r),
            r * (-1) * Math.cos(teta * r)
        );
    }
}
