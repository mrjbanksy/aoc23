package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay4 {

    private Day4 day4;

    @BeforeEach
    public void setup() {
        day4 = new Day4();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day4.getPuzzleLines("day4.txt");
        assertEquals(13, day4.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day4.getPuzzleLines("day4.txt");
        assertEquals(30, day4.solve2Impl(it));
    }
}
