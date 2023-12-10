package edu.project4;

import edu.project4.colors.Color;
import edu.project4.image.FractalImage;
import edu.project4.imageprocessing.LogGammaCorrection;
import edu.project4.rendering.MultiThreadedRenderer;
import edu.project4.rendering.Renderer;
import edu.project4.rendering.SingleThreadedRenderer;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlameGenerationRunner {
    private static final Logger LOGGER = LogManager.getLogger();

    private FlameGenerationRunner() {
    }

    @SuppressWarnings({"ParameterNumber", "MagicNumber"})
    static void generateFractalFlame(
        int nThreads,
        int pointsNumber,
        int iterationNumber,
        int affineNumber,
        double gamma,
        int symmetry,
        List<Color> colors,
        Transformation nonLinearTransformation,
        Path filename
    ) throws IOException {
        if (nThreads <= 0) {
            throw new IllegalArgumentException("Number of threads must be positive");
        }

        Renderer renderer = nThreads == 1 ? new SingleThreadedRenderer() : new MultiThreadedRenderer(nThreads);
        List<AffineTransformation> affineTransformationList =
            renderer.generateAffineTransformations(affineNumber, colors);

        long startTime = System.nanoTime();
        FractalImage image = renderer.render(
            pointsNumber,
            iterationNumber,
            1920,
            1080,
            symmetry,
            affineTransformationList,
            nonLinearTransformation
        );
        long endTime = System.nanoTime();

        LOGGER.info("Rendering time: " + (endTime - startTime) / 1e9 + " seconds");

        LogGammaCorrection correction = new LogGammaCorrection(gamma);
        correction.process(image);

        ImageUtils.save(image, filename, ImageFormat.PNG);
    }
}
