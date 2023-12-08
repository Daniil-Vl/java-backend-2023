package edu.project4.transformations;

import edu.project4.image.Point;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.Range;

/**
 * Transforms point like this:
 * x1 = a*x0 + b*y0 + c
 * y1 = d*x0 + e*y0 + f
 */
@SuppressWarnings("MultipleVariableDeclarations")
public class AffineTransformation implements Transformation {
    private static final List<Color> colors = List.of(
        Color.GREEN, Color.RED
    );
    /**
     * Coefficients for affine transformation
     */
    @Range(from = -1, to = 1)
    private double a, b, c, d, e, f;
    @Range(from = 0, to = 255)
    private int red, green, blue;

    private static final double LINEAR_TRANSFORMATION_BOTTOM_EDGE = -1.0;
    private static final double LINEAR_TRANSFORMATION_UPPER_EDGE = 1.0;
    private static final double TRANSLATION_TRANSFORMATION_BOTTOM_EDGE = -1;
    private static final double TRANSLATION_TRANSFORMATION_UPPER_EDGE = 1;
    private static final int COLOR_UPPER_EDGE = 256;

    /**
     * Generate random coefficients using randomizer with System.nanoTime() as seed
     */
    public AffineTransformation() {
        this(ThreadLocalRandom.current().nextLong(-100_000, 100_000));
    }

    /**
     * Generate random coefficients using randomizer with given seed
     */
    public AffineTransformation(long seed) {
        Random random = new Random(seed);

        // Generate a, b, d, e
        do {
            a = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            b = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            d = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            e = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
        } while (
            sqr(a) + sqr(d) >= 1 || sqr(b) + sqr(e) >= 1
                || sqr(a) + sqr(b) + sqr(d) + sqr(e) >= 1 + sqr(a * e + b * d)
        );

        // Generate c, f
        this.c = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);
        this.f = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);

        // Generate red, green, blue
        Color randomColor = colors.get(random.nextInt(colors.size()));
//        Color randomColor = Color.BLUE;
        this.red = randomColor.getRed();
        this.green = randomColor.getGreen();
        this.blue = randomColor.getBlue();
//        this.red = random.nextInt(COLOR_UPPER_EDGE);
//        this.green = random.nextInt(COLOR_UPPER_EDGE);
//        this.blue = random.nextInt(COLOR_UPPER_EDGE);
    }

    private static double sqr(double value) {
        return value * value;
    }

    @Override
    public Point apply(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;
        return new Point(newX, newY);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
