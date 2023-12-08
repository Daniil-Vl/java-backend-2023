package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.imageprocessing.LogGammaCorrection;
import edu.project4.rendering.SingleThreadedRenderer;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.utils.ImageFormat;
import edu.project4.utils.ImageUtils;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;

public class Main {

    static void generateWithSingleThreadedRenderer() throws IOException {
        int pointsNumber = 50_000;
        int iterationNumber = 10_000;
        int affineNumber = 5;
        double gamma = 2.5;

        SingleThreadedRenderer renderer = new SingleThreadedRenderer();
        List<AffineTransformation> affineTransformationList = renderer.generateAffineTransformations(affineNumber);
//
//        FractalImage image = renderer.render(
//            pointsNumber,
//            iterationNumber,
//            1920,
//            1080,
//            affineTransformationList,
//            System.nanoTime(),
////            new SinusoidalTransformation()
////            new DiskTransformation()
////            new HeartTransformation()
//            new SphericalTransformation()
////            new SwirlTransformation()
//        );

        FractalImage image = renderer.render(
            pointsNumber,
            iterationNumber,
            1920,
            1080,
            5125,
            affineTransformationList,
            new SphericalTransformation()
        );

        LogGammaCorrection correction = new LogGammaCorrection(gamma);
        correction.process(image);

        Path filename = Path.of("src", "main", "resources", "project4", "single_threaded_fractal.jpeg");
        ImageUtils.save(
            image,
            filename,
            ImageFormat.JPEG
        );

        Desktop.getDesktop().open(filename.toFile());
    }

    static void temp() throws IOException {
        Path filename = Path.of("src", "main", "resources", "project4", "temp_fractal.jpeg");
        BufferedImage image = new BufferedImage(1, 1, 1);
        image.setRGB(0, 0, new Color(10, 10, 10).getRGB());

        ImageIO.write(image, "jpeg", filename.toFile());
    }

    public static void main(String[] args) throws IOException {
        generateWithSingleThreadedRenderer();
    }
}
