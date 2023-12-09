package edu.project4;

import edu.project4.colors.Color;
import edu.project4.image.FractalImage;
import edu.project4.imageprocessing.LogGammaCorrection;
import edu.project4.rendering.MultiThreadedRenderer;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.ExponentialTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.TangentTransformation;
import edu.project4.transformations.Transformation;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlameGenerationRunner {
    private static final Logger LOGGER = LogManager.getLogger();

    private FlameGenerationRunner() {
    }

    @SuppressWarnings("MagicNumber")
    static void generateFractalFlame(
        int pointsNumber,
        int iterationNumber,
        int affineNumber,
        double gamma,
        List<Color> colors,
        Transformation nonLinearTransformation,
        Path filename
    ) throws IOException {
        MultiThreadedRenderer renderer = new MultiThreadedRenderer();
        List<AffineTransformation> affineTransformationList =
            renderer.generateAffineTransformations(affineNumber, colors);

        LOGGER.info("Start rendering...");
        long startTime = System.nanoTime();
        FractalImage image = renderer.render(
            pointsNumber,
            iterationNumber,
            1920,
            1080,
            affineTransformationList,
            nonLinearTransformation
        );
        long endTime = System.nanoTime();
        LOGGER.info("Finish rendering");
        LOGGER.info("Rendering time: " + (endTime - startTime) / 1e9 + " seconds");

        LogGammaCorrection correction = new LogGammaCorrection(gamma);
        correction.process(image);

        ImageUtils.save(image, filename, ImageFormat.PNG);
    }

    @SuppressWarnings("MagicNumber")
    static void generateRandomFlames(int count) throws IOException {
        int lowerPointsNumberEdge = 20000;
        int upperPointsNumberEdge = 70000;
        int lowerIterationsNumberEdge = 20000;
        int upperIterationsNumberEdge = 70000;
        int lowerAffineNumberEdge = 3;
        int upperAffineNumberEdge = 15;
        int lowerColorNumberEdge = 1;
        int upperColorNumberEdge = 6;
        double lowerGammaEdge = 0.3;
        double upperGammaEdge = 1.0;
        List<Color> availableColors =
            List.of(Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PURPLE, Color.WHITE);
        List<Transformation> availableNonLinearTransformations =
            List.of(new SphericalTransformation(), new ExponentialTransformation(), new TangentTransformation());
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 1; i <= count; i++) {
            List<Color> colors = new ArrayList<>();
            for (int j = 0; j < random.nextInt(lowerColorNumberEdge, upperColorNumberEdge); j++) {
                colors.add(availableColors.get(random.nextInt(availableColors.size())));
            }

            generateFractalFlame(
                random.nextInt(lowerPointsNumberEdge, upperPointsNumberEdge),
                random.nextInt(lowerIterationsNumberEdge, upperIterationsNumberEdge),
                random.nextInt(lowerAffineNumberEdge, upperAffineNumberEdge),
                random.nextDouble(lowerGammaEdge, upperGammaEdge),
                colors,
                availableNonLinearTransformations.get(random.nextInt(availableNonLinearTransformations.size())),
                Path.of("src", "main", "resources", "project4", "flames_batch", "fractal_%d.png".formatted(i))
            );
        }

        long endTime = System.nanoTime();
        LOGGER.info("Entire generation time - {} minutes", (endTime - startTime) / (1e9 * 60));
    }
}
