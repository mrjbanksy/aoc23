package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

public class Day1 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day1.txt");
        int calibrationValues = solve1Impl(it);
        System.out.println("The total calibration value is " + calibrationValues);
    }

    int solve1Impl(LineIterator it) {
        int calibrationValues = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            line = line.replaceAll("[^0-9]", "");
            calibrationValues += getCalibrationValue(line);
        }
        return calibrationValues;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day1.txt");
        int calibrationValues = solve2Impl(it);
        System.out.println("The total calibration value is " + calibrationValues);
    }

    int solve2Impl(LineIterator it) {
        int calibrationValues = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            line = lettersToNumbers(line);
            calibrationValues += getCalibrationValue(line);
        }
        return calibrationValues;
    }

    int getCalibrationValue(String line) {
        char firstDigit = line.charAt(0);
        char lastDigit = line.charAt(line.length() - 1);
        return Integer.parseInt(String.format("%c%c", firstDigit, lastDigit));
    }

    // It's possible that there is overlap between the last character of one number and the first character of the next
    // number - for example twone or sevenine. The substring therefore needs to include the last character for those
    // potential overlaps, except for four and six, because no digit will start with r or x.
    String lettersToNumbers(String line) {
        if (line.isEmpty()) {
            return line;
        }
        if (line.startsWith("one")) {
            return "1" + lettersToNumbers(line.substring(2));
        }
        if (line.startsWith("two")) {
            return "2" + lettersToNumbers(line.substring(2));
        }
        if (line.startsWith("three")) {
            return "3" + lettersToNumbers(line.substring(4));
        }
        if (line.startsWith("four")) {
            return "4" + lettersToNumbers(line.substring(4));
        }
        if (line.startsWith("five")) {
            return "5" + lettersToNumbers(line.substring(3));
        }
        if (line.startsWith("six")) {
            return "6" + lettersToNumbers(line.substring(3));
        }
        if (line.startsWith("seven")) {
            return "7" + lettersToNumbers(line.substring(4));
        }
        if (line.startsWith("eight")) {
            return "8" + lettersToNumbers(line.substring(4));
        }
        if (line.startsWith("nine")) {
            return "9" + lettersToNumbers(line.substring(3));
        }
        if (Character.isDigit(line.charAt(0))) {
            return line.charAt(0) + lettersToNumbers(line.substring(1));
        }
        return lettersToNumbers(line.substring(1));
    }
}
