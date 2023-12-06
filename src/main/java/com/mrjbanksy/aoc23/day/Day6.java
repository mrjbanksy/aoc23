package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

import java.util.Arrays;
import java.util.List;

public class Day6 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day6.txt");
        long marginOfError = solve1Impl(it);
        System.out.println("The margin for error is " + marginOfError);
    }

    long solve1Impl(LineIterator it) {
        long marginOfError = 1;
        String raceTimeString = it.nextLine();
        String currentRecordString = it.nextLine();
        List<Long> raceTimes = parseLine(raceTimeString);
        List<Long> currentRecords = parseLine(currentRecordString);
        for (int i = 0; i < raceTimes.size(); i++) {
            marginOfError *= getNumberOfWaysToBeatRecord(raceTimes.get(i), currentRecords.get(i));
        }
        return marginOfError;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day6.txt");
        long marginOfError = solve2Impl(it);
        System.out.println("The margin for error is " + marginOfError);
    }

    long solve2Impl(LineIterator it) {
        String raceTimeString = it.nextLine();
        String currentRecordString = it.nextLine();
        long raceTime = parseLineWithBadKerning(raceTimeString);
        long currentRecord = parseLineWithBadKerning(currentRecordString);
        return getNumberOfWaysToBeatRecord(raceTime, currentRecord);
    }

    long getNumberOfWaysToBeatRecord(long raceTime, long currentRecord) {
        double possibleTime1 = (-raceTime + Math.sqrt(Math.pow(raceTime, 2.0) - 4 * currentRecord)) / -2;
        double possibleTime2 = (-raceTime - Math.sqrt(Math.pow(raceTime, 2.0) - 4 * currentRecord)) / -2;
        long minimumTime;
        long maximumTime;
        if (possibleTime1 > 0 && possibleTime2 > 0) {
            minimumTime = Double.valueOf(Math.ceil(Math.min(possibleTime1, possibleTime2))).longValue();
            maximumTime = Double.valueOf(Math.ceil(Math.max(possibleTime1, possibleTime2))).longValue();
        } else if (possibleTime1 > 0) {
            minimumTime = Double.valueOf(Math.ceil(possibleTime1)).intValue();
            maximumTime = raceTime - 1;
        } else if (possibleTime2 > 0) {
            minimumTime = Double.valueOf(Math.ceil(possibleTime2)).intValue();
            maximumTime = raceTime - 1;
        } else {
            throw new RuntimeException("There is no valid time :(");
        }
        if ((raceTime - maximumTime) * maximumTime == currentRecord) {
            maximumTime--;
        }
        return maximumTime - minimumTime;
    }

    List<Long> parseLine(String line) {
        return Arrays.stream(line.split(":")[1].trim().replaceAll("\\s+", " ").split(" ")).map(Long::parseLong).toList();
    }

    long parseLineWithBadKerning(String line) {
        return Long.parseLong(line.split(":")[1].replaceAll("\\s+", ""));
    }

}
