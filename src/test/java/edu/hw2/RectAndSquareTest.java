package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RectAndSquareTest {

    private static Stream<Arguments> rectangles() {
        return Stream.of(
            Arguments.of(new Rectangle(2, 3)),
            Arguments.of(new Square(10))
        );
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void testFromTask(Rectangle rect) {
        rect = rect.setWidth(10);
        rect = rect.setHeight(20);
        assertThat(rect.area()).isEqualTo(200);
    }

    @Test
    void testRectangle() {
        Rectangle rect = new Rectangle(2, 4);
        assertThat(rect.area()).isEqualTo(8);
    }

    @Test
    void testSquare() {
        Square square = new Square(10);
        assertThat(square.area()).isEqualTo(100);
    }
}
