package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import java.util.List;

public abstract class AbstractRenderer implements Renderer {
    @Override
    public abstract FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        int symmetry,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    );

    protected Pixel changePixelColor(Pixel pixel, AffineTransformation affineTransformation) {
        if (pixel.hitCount() == 0) {
            return pixel.updateColors(
                affineTransformation.getRed(),
                affineTransformation.getGreen(),
                affineTransformation.getBlue()
            );
        }

        return pixel.updateColors(
            (pixel.red() + affineTransformation.getRed()) / 2,
            (pixel.green() + affineTransformation.getGreen()) / 2,
            (pixel.blue() + affineTransformation.getBlue()) / 2
        );

    }
}
