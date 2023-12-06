package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay5 {

    private Day5 day5;

    @BeforeEach
    public void setup() {
        day5 = new Day5();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day5.getPuzzleLines("day5.txt");
        assertEquals(35, day5.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day5.getPuzzleLines("day5.txt");
        assertDoesNotThrow(() -> day5.solve2Impl(it));
    }
}
