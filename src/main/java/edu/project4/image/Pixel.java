package edu.project4.image;

public record Pixel(int red, int green, int blue, int hitCount) {
    public Pixel updateColors(int red, int green, int blue) {
        return new Pixel(red, green, blue, hitCount);
    }

    public Pixel incrementHitCountAndGet() {
        return new Pixel(red, green, blue, hitCount + 1);
    }
}
