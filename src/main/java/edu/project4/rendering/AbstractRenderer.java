package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;

public abstract class AbstractRenderer implements Renderer {
    @Override
    public abstract FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    );

    protected void changePixelColor(FractalImage image, AffineTransformation affineTransformation, int x, int y) {
        if (image.contains(x, y)) {
            Pixel pixel = image.pixel(x, y);

            if (pixel.hitCount() == 0) {
                pixel = pixel.updateColors(
                    affineTransformation.getRed(),
                    affineTransformation.getGreen(),
                    affineTransformation.getBlue()
                );
            } else {
                pixel = pixel.updateColors(
                    (pixel.red() + affineTransformation.getRed()) / 2,
                    (pixel.green() + affineTransformation.getGreen()) / 2,
                    (pixel.blue() + affineTransformation.getBlue()) / 2
                );
            }

            image.setPixel(x, y, pixel.incrementHitCountAndGet());
        }
    }
}
