package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay6 {

    private Day6 day6;

    @BeforeEach
    public void setup() {
        day6 = new Day6();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day6.getPuzzleLines("day6.txt");
        assertEquals(288, day6.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day6.getPuzzleLines("day6.txt");
        assertEquals(71503, day6.solve2Impl(it));
    }
}
