package edu.project4.image;

public record Pixel(int red, int green, int blue, int hitCount) {
    public Pixel update(int red, int green, int blue) {
        return new Pixel(red, green, blue, hitCount);
    }

    public Pixel hit() {
        return new Pixel(red, green, blue, hitCount + 1);
    }
}
