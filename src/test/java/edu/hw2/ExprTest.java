package edu.hw2;

import edu.hw2.task1.Addition;
import edu.hw2.task1.Constant;
import edu.hw2.task1.Exponent;
import edu.hw2.task1.Expr;
import edu.hw2.task1.Multiplication;
import edu.hw2.task1.Negate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// TODO Add test for edge cases for some operators
class ExprTest {
    @Test
    void Addition() {
        Expr add = new Addition(new Constant(1), new Constant(2));
        assertThat(add.evaluate()).isEqualTo(3);
    }

    @Test
    void Multiplication() {
        Expr mult = new Multiplication(new Constant(2), new Constant(4));
        assertThat(mult.evaluate()).isEqualTo(8);
    }

    @Test
    void Exponent() {
        Expr exp = new Exponent(new Constant(2), new Constant(4));
        assertThat(exp.evaluate()).isEqualTo(16);
    }

    @Test
    void testNegate() {
        Expr exp = new Negate(new Constant(10));
        assertThat(exp.evaluate()).isEqualTo(-10);
    }

    @Test
    void complexExpression() {
        Expr exp = new Exponent(
            new Addition(new Constant(1), new Constant(2)),
            new Multiplication(new Constant(4), new Constant(3))
        );
        assertThat(exp.evaluate()).isEqualTo(Math.pow(1 + 2, 4 * 3));
    }

    @Test
    void testDivisionByZero() {
        Expr expr = new Exponent(
            new Constant(0),
            new Constant(-1)
        );
        assertThat(expr.evaluate()).isInfinite();
    }

    @Test
    void testSquareRootFromNegativeNumber() {
        Expr expr = new Exponent(
            new Constant(-1),
            new Constant(0.5)
        );
        assertThat(expr.evaluate()).isNaN();
    }

}
