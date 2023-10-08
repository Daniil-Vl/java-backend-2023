package edu.hw1.task3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {

    @Test
    void nestable1() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {0, 6};
        assertThat(Task3.isNestable(inner, outer)).isTrue();
    }

    @Test
    void nestable2() {
        int[] inner = {3, 1};
        int[] outer = {4, 0};
        assertThat(Task3.isNestable(inner, outer)).isTrue();
    }

    @Test
    void notNestable1() {
        int[] inner = {9, 9, 8};
        int[] outer = {8, 9};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void notNestable2() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {2, 3};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void innerMinLessThanOuterMin() {
        int[] inner = {1, 4};
        int[] outer = {2, 3, 5};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void innerMaxMoreThanOuterMax() {
        int[] inner = {1, 4};
        int[] outer = {2, 3};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void innerEmpty() {
        int[] inner = {};
        int[] outer = {2, 3};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void outerEmpty() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

    @Test
    void bothEmpty() {
        int[] inner = {};
        int[] outer = {};
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

}
