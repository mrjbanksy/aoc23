package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay2 {

    private Day2 day2;

    @BeforeEach
    public void setup() {
        day2 = new Day2();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day2.getPuzzleLines("day2.txt");
        assertEquals(8, day2.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day2.getPuzzleLines("day2.txt");
        assertEquals(2286, day2.solve2Impl(it));
    }
}
