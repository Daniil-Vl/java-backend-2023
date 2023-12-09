package edu.project4.transformation;

import edu.project4.image.Point;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TransformationTest {
    @Test
    void eachNonLinearTransformationChangesPoint() {
        Random random = new Random();

        for (NonLinearTransformationType type : NonLinearTransformationType.values()) {
            Transformation transformation = NonLinearTransformationsFactory.createTransformation(type);

            Point initialPoint = new Point(
                random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE),
                random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)
            );

            Point transformedPoint = transformation.apply(initialPoint);

            assertThat(transformedPoint).isNotEqualTo(initialPoint);
        }

    }
}
