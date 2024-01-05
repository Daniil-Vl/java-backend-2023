package edu.hw10.task1.utilityclasses;

import edu.hw10.task1.myannotations.Max;
import edu.hw10.task1.myannotations.Min;

public record Rectangle(
    int x,
    int y,
    @Min(value = 0) @Max(value = 10) int width,
    @Min(value = 0) @Max(value = 10) int height) {
}
