package edu.project4.rendering;

import edu.project4.colors.Color;
import edu.project4.image.FractalImage;
import edu.project4.image.Pixel;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.NonLinearTransformationType;
import edu.project4.transformation.NonLinearTransformationsFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

abstract class RendererTest {

    private Renderer renderer;

    @BeforeEach
    void init() {
        this.renderer = getInstance();
    }

    abstract Renderer getInstance();

    @Test
    void testColor() {
        int pointsNumber = 100;
        int iterationNumber = 100;
        Color color = Color.BLUE;
        FractalImage image = renderer.render(pointsNumber, iterationNumber, 10, 10,
            List.of(new AffineTransformation(List.of(color))),
            NonLinearTransformationsFactory.createTransformation(NonLinearTransformationType.EXPONENTIAL)
        );

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                assertThat(isBlueOrBlack(image.pixel(x, y))).isTrue();
            }
        }
    }

    private boolean isBlueOrBlack(Pixel pixel) {
        return pixel.red() == Color.BLUE.getRed()
            && pixel.green() == Color.BLUE.getGreen()
            && pixel.blue() == Color.BLUE.getBlue()
            || pixel.red() == Color.BLACK.getRed()
            && pixel.green() == Color.BLACK.getGreen()
            && pixel.blue() == Color.BLACK.getBlue();

    }

}
