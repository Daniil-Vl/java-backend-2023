package edu.project4.image;

import java.util.Objects;

public final class FractalImage {
    private final Pixel[][] field;
    private final int width;
    private final int height;

    public FractalImage(Pixel[][] field, int width, int height) {
        this.field = field;
        this.width = width;
        this.height = height;
    }


    /**
     * Create FractalImage with black background
     *
     * @param width  - width of the image
     * @param height - height of the image
     * @return fractal image with black background
     */
    public static FractalImage create(int width, int height) {
        Pixel[][] tempField = new Pixel[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tempField[y][x] = new Pixel(0, 0, 0, 0);
            }
        }

        return new FractalImage(tempField, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public Pixel pixel(int x, int y) {
        return this.field[y][x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        this.field[y][x] = pixel;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FractalImage) obj;
        return Objects.equals(this.field, that.field) &&
                this.width == that.width &&
                this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, width, height);
    }

    @Override
    public String toString() {
        return "FractalImage[" +
                "field=" + field + ", " +
                "width=" + width + ", " +
                "height=" + height + ']';
    }

}
