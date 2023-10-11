package edu.hw2.task1;

public record Negate(Expr expression) implements Expr {

    @Override
    public double evaluate() {
        return -1 * this.expression.evaluate();
    }
}
