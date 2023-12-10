package edu.project4.rendering;

import edu.project4.colors.Color;
import edu.project4.image.FractalImage;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.stream.Stream;

@FunctionalInterface
public interface Renderer {

    FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        int symmetry,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    );

    default List<AffineTransformation> generateAffineTransformations(int n, List<Color> colors) {
        return Stream.generate(() -> new AffineTransformation(colors)).limit(n).toList();
    }
}
