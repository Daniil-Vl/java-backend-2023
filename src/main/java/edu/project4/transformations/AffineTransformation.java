package edu.project4.transformations;

import edu.project4.colors.Color;
import edu.project4.image.Point;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Range;

/**
 * Transforms point like this:
 * x1 = a*x0 + b*y0 + c
 * y1 = d*x0 + e*y0 + f
 */
@SuppressWarnings("MultipleVariableDeclarations")
public class AffineTransformation implements Transformation {
    private static final double LINEAR_TRANSFORMATION_BOTTOM_EDGE = -1.0;
    private static final double LINEAR_TRANSFORMATION_UPPER_EDGE = 1.0;
    private static final double TRANSLATION_TRANSFORMATION_BOTTOM_EDGE = -1;
    private static final double TRANSLATION_TRANSFORMATION_UPPER_EDGE = 1;
    @Range(from = 0, to = 255)
    private final int red, green, blue;
    private final double c;
    private final double f;
    private double a;
    private double b;
    private double d;
    private double e;

    /**
     * Generate random coefficients using randomizer with System.nanoTime() as seed
     */
    public AffineTransformation() {
        this(System.nanoTime(), List.of(Color.WHITE));
    }

    /**
     * Generate random coefficients using randomizer with given seed
     */
    public AffineTransformation(long seed, List<Color> colors) {
        Random random = new Random(seed);

        // Generate a, b, d, e
        do {
            a = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            b = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            d = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
            e = random.nextDouble(LINEAR_TRANSFORMATION_BOTTOM_EDGE, LINEAR_TRANSFORMATION_UPPER_EDGE);
        } while (
            !isCoefficientValid()
        );

        // Generate c, f
        this.c = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);
        this.f = random.nextDouble(TRANSLATION_TRANSFORMATION_BOTTOM_EDGE, TRANSLATION_TRANSFORMATION_UPPER_EDGE);

        // Generate rgb color
        Color randomColor = colors.get(random.nextInt(colors.size()));
        this.red = randomColor.getRed();
        this.green = randomColor.getGreen();
        this.blue = randomColor.getBlue();
    }

    private boolean isCoefficientValid() {
        return a * a + d * d < 1
            && b * b + e * e < 1
            && a * b + d * e < 1 + (a * e + b * d) * (a * e + b * d);
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
