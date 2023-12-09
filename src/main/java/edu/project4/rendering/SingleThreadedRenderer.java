package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import edu.project4.image.Point;
import edu.project4.image.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadedRenderer implements Renderer {

    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;

    @Override
    public FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        long seed,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    ) {
        Random random = new Random(seed);
        Rect biUnitRect = new Rect(X_MIN, Y_MIN, X_MAX - X_MIN, Y_MAX - Y_MIN);
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);

        for (int num = 0; num < pointsNumber; num++) {
            double newX = random.nextDouble(X_MIN, X_MAX);
            double newY = random.nextDouble(Y_MIN, Y_MAX);
            Point point = new Point(newX, newY);

            for (int step = -20; step < iterationNumber; step++) {
                AffineTransformation affineTransformation = affineTransformations.get(random.nextInt(
                    affineTransformations.size()));

                point = affineTransformation.apply(point);
                point = nonLinearTransformation.apply(point);

                if (step >= 0 && biUnitRect.contains(point)) {
                    int x1 = imageWidth - (int) Math.floor(
                        imageWidth * ((X_MAX - point.x()) / (X_MAX - X_MIN))
                    );
                    int y1 = imageHeight - (int) Math.floor(
                        imageHeight * ((Y_MAX - point.y()) / (Y_MAX - Y_MIN))
                    );

                    if (resultImage.contains(x1, y1)) {
                        Pixel pixel = resultImage.pixel(x1, y1);
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
                        resultImage.setPixel(x1, y1, pixel.incrementHitCountAndGet());
                    }
                }
            }
        }

        return resultImage;
    }
}
