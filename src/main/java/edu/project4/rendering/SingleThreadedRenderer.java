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

    // TODO remove this constants and use instead given imageWidth and imageHeight
    private static final double XMIN = -1.777;
    private static final double XMAX = 1.777;
    private static final double YMIN = -1;
    private static final double YMAX = 1;

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
        Random random = new Random();
        Rect biUnitRect = new Rect(XMIN, YMIN, XMAX - XMIN, YMAX - YMIN);
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);

        for (int num = 0; num < pointsNumber; num++) {
            double newX = random.nextDouble(XMIN, XMAX);
            double newY = random.nextDouble(YMIN, YMAX);
            Point point = new Point(newX, newY);

            for (int step = -20; step < iterationNumber; step++) {
                AffineTransformation affineTransformation = affineTransformations.get(random.nextInt(
                    affineTransformations.size()));

                point = affineTransformation.apply(point);
                point = nonLinearTransformation.apply(point);

                if (step >= 0 && biUnitRect.contains(point)) {
                    int x1 = imageWidth - (int) Math.floor(
                        imageWidth * ((XMAX - point.x()) / (XMAX - XMIN))
                    );
                    int y1 = imageHeight - (int) Math.floor(
                        imageHeight * ((YMAX - point.y()) / (YMAX - YMIN))
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
