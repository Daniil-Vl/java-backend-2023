package edu.project4.transformation.nonlineartransformations;

import edu.project4.image.Point;
import edu.project4.transformation.Transformation;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y()),
            Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y())
        );
    }
}
