package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay7 {

    private Day7 day7;

    @BeforeEach
    public void setup() {
        day7 = new Day7();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day7.getPuzzleLines("day7.txt");
        assertEquals(6440, day7.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day7.getPuzzleLines("day7.txt");
        assertEquals(5905, day7.solve2Impl(it));
    }
}
