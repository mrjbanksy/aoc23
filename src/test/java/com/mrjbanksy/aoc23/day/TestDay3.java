package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay3 {

    private Day3 day3;

    @BeforeEach
    public void setup() {
        day3 = new Day3();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day3.getPuzzleLines("day3.txt");
        assertEquals(4361, day3.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day3.getPuzzleLines("day3.txt");
        assertEquals(467835, day3.solve2Impl(it));
    }
}
