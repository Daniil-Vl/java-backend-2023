package edu.project4.imageprocessing;

import edu.project4.image.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    /**
     * In-place image post-processing
     *
     * @param image to process
     */
    void process(FractalImage image);
}
