package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.image.Point;
import edu.project4.image.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadedRenderer extends AbstractRenderer {
    private static final int RENDER_STEP_THRESHOLD = 20;
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
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    ) {
        Random random = new Random();
        Rect biUnitRect = new Rect(X_MIN, Y_MIN, X_MAX - X_MIN, Y_MAX - Y_MIN);
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);

        for (int num = 0; num < pointsNumber; num++) {
            double newX = random.nextDouble(X_MIN, X_MAX);
            double newY = random.nextDouble(Y_MIN, Y_MAX);
            Point point = new Point(newX, newY);

            for (int step = 0; step < iterationNumber; step++) {
                AffineTransformation affineTransformation = affineTransformations.get(random.nextInt(
                    affineTransformations.size()));

                point = affineTransformation.apply(point);
                point = nonLinearTransformation.apply(point);

                if (step > RENDER_STEP_THRESHOLD && biUnitRect.contains(point)) {
                    int x1 = imageWidth - (int) Math.floor(
                        imageWidth * ((X_MAX - point.x()) / (X_MAX - X_MIN))
                    );
                    int y1 = imageHeight - (int) Math.floor(
                        imageHeight * ((Y_MAX - point.y()) / (Y_MAX - Y_MIN))
                    );

                    changePixelColor(resultImage, affineTransformation, x1, y1);
                }
            }
        }

        return resultImage;
    }
}
