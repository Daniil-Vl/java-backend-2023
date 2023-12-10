package edu.project4.transformation;

import edu.project4.image.Point;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AffineTransformationTest {

    @Test
    void affineTransformation() {
        Point initialPoint = new Point(0, 0);
        AffineTransformation affineTransformation = new AffineTransformation();
        Point pointAfterTransformation = affineTransformation.apply(initialPoint);

        assertThat(pointAfterTransformation).isNotEqualTo(initialPoint);
    }
}
