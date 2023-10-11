package edu.hw2.task1;

public record Multiplication(Expr left, Expr right) implements Expr {

    @Override
    public double evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}
