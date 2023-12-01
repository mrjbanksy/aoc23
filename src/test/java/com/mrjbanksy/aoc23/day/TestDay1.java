package com.mrjbanksy.aoc23.day;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestDay1 {

    private Day1 day1;

    @BeforeEach
    public void setup() {
        day1 = new Day1();
    }

    @Test
    public void testSolve1Impl() {
        LineIterator it = day1.getPuzzleLines("day1_1.txt");
        assertEquals(142, day1.solve1Impl(it));
    }

    @Test
    public void testSolve2Impl() {
        LineIterator it = day1.getPuzzleLines("day1_2.txt");
        assertEquals(281, day1.solve2Impl(it));
    }

    @Test
    public void testLettersToNumber() {
        assertEquals("219", day1.lettersToNumbers("two1nine"));
        assertEquals("823", day1.lettersToNumbers("eightwothree"));
        assertEquals("123", day1.lettersToNumbers("abcone2threexyz"));
        assertEquals("179", day1.lettersToNumbers("1sevenine"));
    }
}
