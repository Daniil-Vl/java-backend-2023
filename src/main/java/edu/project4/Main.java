package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.imageprocessing.LogGammaCorrection;
import edu.project4.rendering.SingleThreadedRenderer;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    static void generateWithSingleThreadedRenderer() throws IOException {
        int pointsNumber = 50_000;
        int iterationNumber = 15_000;
        int affineNumber = 5;
        double gamma = 0.5;

        SingleThreadedRenderer renderer = new SingleThreadedRenderer();
        List<AffineTransformation> affineTransformationList = renderer.generateAffineTransformations(affineNumber);

        long startTime = System.nanoTime();
        FractalImage image = renderer.render(
            pointsNumber,
            iterationNumber,
            1920,
            1080,
            System.nanoTime(),
            affineTransformationList,
            new SphericalTransformation()
        );
        System.out.println("Rendering time: " + (System.nanoTime() - startTime) / 1e9 + " seconds");

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

    public static void main(String[] args) throws IOException {
        generateWithSingleThreadedRenderer();
    }
}
