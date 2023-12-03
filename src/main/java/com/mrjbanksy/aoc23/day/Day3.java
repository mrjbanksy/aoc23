package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day3.txt");
        int sumPartNumbers = solve1Impl(it);
        System.out.println("The sum of the part numbers is " + sumPartNumbers);
    }

    int solve1Impl(LineIterator it) {
        List<Symbol> symbols = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        parseSchematic(it, symbols, parts);
        AtomicInteger sumPartNumbers = new AtomicInteger();
        parts.forEach(
                p -> {
                    if (symbols.stream().anyMatch(s -> Math.abs(p.y - s.y) <= 1 && p.minX - 1 <= s.x && p.maxX + 1 > s.x)) {
                        sumPartNumbers.addAndGet(p.partNumber);
                    }
                }
        );
        return sumPartNumbers.get();
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day3.txt");
        int sumPartNumbers = solve2Impl(it);
        System.out.println("The sum of all the gear ratios is " + sumPartNumbers);
    }

    int solve2Impl(LineIterator it) {
        List<Symbol> symbols = new ArrayList<>();
        List<Part> parts = new ArrayList<>();
        parseSchematic(it, symbols, parts);
        AtomicInteger gearRatioSum = new AtomicInteger();
        symbols.stream().filter(s -> '*' == s.symbolType).forEach(
                s -> {
                    List<Part> gears = parts.stream().filter(p -> Math.abs(p.y - s.y) <= 1 && p.minX - 1 <= s.x && p.maxX + 1 > s.x).toList();
                    if (gears.size() == 2) {
                        gearRatioSum.addAndGet(gears.get(0).partNumber * gears.get(1).partNumber);
                    }
                }
        );
        return gearRatioSum.get();
    }

    private void parseSchematic(LineIterator it, List<Symbol> symbols, List<Part> parts) {
        int y = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            for (int x = 0; x < line.length(); x++) {
                if (!Character.isDigit(line.charAt(x)) && '.' != line.charAt(x)) {
                    Symbol symbol = new Symbol(x, y, line.charAt(x));
                    symbols.add(symbol);
                } else if (Character.isDigit(line.charAt(x))) {
                    int partNumberLength = 1;
                    while (x + partNumberLength < line.length() && Character.isDigit(line.charAt(x + partNumberLength))) {
                        partNumberLength++;
                    }
                    int partNumber = Integer.parseInt(line.substring(x, x + partNumberLength));
                    Part part = new Part(x, x + partNumberLength, y, partNumber);
                    parts.add(part);
                    x += partNumberLength - 1;
                }
            }
            y++;
        }
    }

    private static class Symbol {
        int x;
        int y;
        char symbolType;

        Symbol(int x, int y, char symbolType) {
            this.x = x;
            this.y = y;
            this.symbolType = symbolType;
        }
    }

    private static class Part {
        int minX;
        int maxX;
        int y;
        int partNumber;

        Part(int minX, int maxX, int y, int partNumber) {
            this.minX = minX;
            this.maxX = maxX;
            this.y = y;
            this.partNumber = partNumber;
        }
    }
}
