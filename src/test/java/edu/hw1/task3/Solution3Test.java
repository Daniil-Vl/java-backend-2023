package edu.hw1.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution3Test {

    @Test
    void nestable1() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {0, 6};
        assertTrue(Solution3.isNestable(inner, outer));
    }

    @Test
    void nestable2() {
        int[] inner = {3, 1};
        int[] outer = {4, 0};
        assertTrue(Solution3.isNestable(inner, outer));
    }

    @Test
    void notNestable1() {
        int[] inner = {9, 9, 8};
        int[] outer = {8, 9};
        assertFalse(Solution3.isNestable(inner, outer));
    }

    @Test
    void notNestable2() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {2, 3};
        assertFalse(Solution3.isNestable(inner, outer));
    }

    @Test
    void innerEmpty() {
        int[] inner = {};
        int[] outer = {2, 3};
        assertFalse(Solution3.isNestable(inner, outer));
    }

    @Test
    void outerEmpty() {
        int[] inner = {1, 2, 3, 4};
        int[] outer = {};
        assertFalse(Solution3.isNestable(inner, outer));
    }

    @Test
    void bothEmpty() {
        int[] inner = {};
        int[] outer = {};
        assertFalse(Solution3.isNestable(inner, outer));
    }

}
