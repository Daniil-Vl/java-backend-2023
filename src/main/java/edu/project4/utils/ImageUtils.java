package edu.project4.utils;

import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage imageToSave = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        // Set colors of all pixels to image
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.red(), pixel.green(), pixel.blue());
                imageToSave.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(imageToSave, format.getStringFormat(), filename.toFile());
    }

    public static void main(String[] args) throws IOException {
        Path filename = Path.of("src", "main", "resources", "project4", "temp_image.png");
        ImageFormat format = ImageFormat.PNG;

        FractalImage image = FractalImage.create(10, 10);

        ImageUtils.save(image, filename, format);
    }
}
