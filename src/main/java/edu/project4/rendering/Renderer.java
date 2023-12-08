package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface Renderer {

    FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        long seed,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    );

    default List<AffineTransformation> generateAffineTransformations(int n) {
        return Stream.generate(AffineTransformation::new).limit(n).toList();
    }
}
