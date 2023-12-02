package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

public class Day2 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day2.txt");
        int sumIdPossibleGames = solve1Impl(it);
        System.out.println("The sum of the IDs of the possible games is " + sumIdPossibleGames);
    }

    int solve1Impl(LineIterator it) {
        int sumIdPossibleGames = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            Game game = new Game(line);
            if (gameIsPossible(game)) {
                sumIdPossibleGames += game.id;
            }
        }
        return sumIdPossibleGames;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day2.txt");
        int power = solve2Impl(it);
        System.out.println("The power of these sets is " + power);
    }

    int solve2Impl(LineIterator it) {
        int power = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            Game game = new Game(line);
            power += (game.maxBlue * game.maxRed * game.maxGreen);
        }
        return power;
    }

    boolean gameIsPossible(Game game) {
        return game.maxRed <= 12 && game.maxGreen <= 13 && game.maxBlue <= 14;
    }

    private static class Game {
        int id;
        int maxBlue;
        int maxRed;
        int maxGreen;

        Game(String gameInfo) {
            maxBlue = Integer.MIN_VALUE;
            maxRed = Integer.MIN_VALUE;
            maxGreen = Integer.MIN_VALUE;
            this.id = Integer.parseInt(gameInfo.split(":")[0].substring(5));
            String[] subsets = gameInfo.split(":")[1].split(";");
            for (String subset : subsets) {
                String[] cubes = subset.split(", ");
                for (String cube : cubes) {
                    int number = Integer.parseInt(cube.trim().split(" ")[0]);
                    String color = cube.trim().split(" ")[1];
                    if ("blue".equals(color) && number > maxBlue) {
                        maxBlue = number;
                    } else if ("red".equals(color) && number > maxRed) {
                        maxRed = number;
                    } else if ("green".equals(color) && number > maxGreen) {
                        maxGreen = number;
                    }
                }
            }
        }
    }
}
