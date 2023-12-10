package edu.project4.image;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point point) {
        return point.x() >= x && point.x() <= x + width
            && point.y() >= y && point.y() <= y + height;
    }
}
