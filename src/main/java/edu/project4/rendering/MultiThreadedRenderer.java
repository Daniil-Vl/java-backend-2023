package edu.project4.rendering;

import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import edu.project4.image.Point;
import edu.project4.image.Rect;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MultiThreadedRenderer extends AbstractRenderer {
    private static final int TIMEOUT = 5;
    private static final int RENDER_STEP_THRESHOLD = 20;
    private static final double X_MIN = -1.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = -1.0;
    private static final double Y_MAX = 1.0;
    private final ExecutorService executorService;
    private final int nThreads;

    public MultiThreadedRenderer() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public MultiThreadedRenderer(int nThreads) {
        this.nThreads = nThreads;
        this.executorService = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public FractalImage render(
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        int symmetry,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    ) {
        FractalImage resultImage = FractalImage.create(imageWidth, imageHeight);
        int pointsPerThread = pointsNumber / nThreads;

        for (int i = 0; i < nThreads; i++) {
            executorService.submit(() -> this.renderTask(
                resultImage,
                pointsPerThread,
                iterationNumber,
                imageWidth,
                imageHeight,
                symmetry,
                affineTransformations,
                nonLinearTransformation
            ));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(TIMEOUT, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return resultImage;
    }

    @SuppressWarnings("ParameterNumber")
    private void renderTask(
        FractalImage resultImage,
        int pointsNumber,
        int iterationNumber,
        int imageWidth,
        int imageHeight,
        int symmetry,
        List<AffineTransformation> affineTransformations,
        Transformation nonLinearTransformation
    ) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Rect biUnitRect = new Rect(X_MIN, Y_MIN, X_MAX - X_MIN, Y_MAX - Y_MIN);

        for (int num = 0; num < pointsNumber; num++) {
            Point point = new Point(random.nextDouble(X_MIN, X_MAX), random.nextDouble(Y_MIN, Y_MAX));

            for (int step = 0; step < iterationNumber; step++) {
                AffineTransformation affineTransformation =
                    affineTransformations.get(random.nextInt(affineTransformations.size()));

                point = affineTransformation.apply(point);
                point = nonLinearTransformation.apply(point);

                if (step <= RENDER_STEP_THRESHOLD || !biUnitRect.contains(point)) {
                    continue;
                }

                Point rotatedPoint;
                double angle = 0.0;

                for (int s = 0; s < symmetry; s++, angle += Math.PI * 2 / symmetry) {
                    rotatedPoint = Point.rotate(point, angle);
                    int x1 = imageWidth - (int) (imageWidth * ((X_MAX - rotatedPoint.x()) / (X_MAX - X_MIN)));
                    int y1 = imageHeight - (int) (imageHeight * ((Y_MAX - rotatedPoint.y()) / (Y_MAX - Y_MIN)));

                    if (!resultImage.contains(x1, y1)) {
                        continue;
                    }

                    Pixel pixel = resultImage.pixel(x1, y1);
                    synchronized (pixel) {
                        resultImage.setPixel(
                            x1,
                            y1,
                            changePixelColor(pixel, affineTransformation).hit()
                        );
                    }
                }
            }
        }
    }
}
