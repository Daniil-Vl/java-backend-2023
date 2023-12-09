package edu.project4;

import edu.project4.colors.Color;
import edu.project4.image.FractalImage;
import edu.project4.imageprocessing.LogGammaCorrection;
import edu.project4.rendering.MultiThreadedRenderer;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlameGenerationRunner {
    private static final Logger LOGGER = LogManager.getLogger();

    private FlameGenerationRunner() {
    }

    @SuppressWarnings("MagicNumber")
    static void generateFractalFlame() throws IOException {
        int pointsNumber = 10_000;
        int iterationNumber = 10_000;
        int affineNumber = 5;
        double gamma = 0.5;

        MultiThreadedRenderer renderer = new MultiThreadedRenderer();
        List<AffineTransformation> affineTransformationList =
            renderer.generateAffineTransformations(affineNumber, List.of(Color.RED, Color.WHITE));

        long startTime = System.nanoTime();
        FractalImage image = renderer.render(
            pointsNumber,
            iterationNumber,
            1920,
            1080,
            affineTransformationList,
            new SphericalTransformation()
        );
        LOGGER.info("Rendering time: " + (System.nanoTime() - startTime) / 1e9 + " seconds");

        LogGammaCorrection correction = new LogGammaCorrection(gamma);
        correction.process(image);

        Path filename = Path.of("src", "main", "resources", "project4", "single_threaded_fractal.png");
        ImageUtils.save(
            image,
            filename,
            ImageFormat.PNG
        );

        Desktop.getDesktop().open(filename.toFile());
    }
}
