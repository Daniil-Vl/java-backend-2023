package edu.project4.transformation.nonlineartransformations;

import edu.project4.image.Point;
import edu.project4.transformation.Transformation;

public class TangentTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(
            Math.sin(x) / Math.cos(y),
            Math.tan(y)
        );
    }
}
