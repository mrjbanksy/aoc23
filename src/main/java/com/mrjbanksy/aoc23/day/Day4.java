package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

import java.util.*;

public class Day4 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day4.txt");
        int sumScratchcards = solve1Impl(it);
        System.out.println("The sum of the scratchcard points is " + sumScratchcards);
    }

    int solve1Impl(LineIterator it) {
        int totalPoints = 0;
        while (it.hasNext()) {
            int cardPoints = 0;
            String line = it.nextLine();
            List<String> winningNumbers = Arrays.stream(line.split(":")[1].trim().split("\\|")[0].trim().split(" ")).toList();
            List<String> numbers = Arrays.stream(line.split(":")[1].trim().split("\\|")[1].trim().replaceAll("  ", " ").split(" ")).toList();
            for (String number : numbers) {
                if (winningNumbers.contains(number)) {
                    if (cardPoints == 0) {
                        cardPoints = 1;
                    } else {
                        cardPoints *= 2;
                    }
                }
            }
            totalPoints += cardPoints;
        }
        return totalPoints;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day4.txt");
        int sumScratchcards = solve2Impl(it);
        System.out.println("The sum of all the scratchcards is " + sumScratchcards);
    }

    int solve2Impl(LineIterator it) {
        int totalScratchcards = 0;
        Map<Integer, Integer> copies = new HashMap<>();
        int currentCard = 0;
        while (it.hasNext()) {
            currentCard++;
            int currentCardCount = 1 + copies.getOrDefault(currentCard, 0);
            String line = it.nextLine();
            List<String> winningNumbers = Arrays.stream(line.split(":")[1].trim().split("\\|")[0].trim().split(" ")).toList();
            List<String> numbers = Arrays.stream(line.split(":")[1].trim().split("\\|")[1].trim().replaceAll("  ", " ").split(" ")).toList();
            long winCount = numbers.stream().filter(winningNumbers::contains).count();
            for (int i = currentCard; i < currentCard + winCount; i++) {
                copies.put(i + 1, copies.getOrDefault(i + 1, 0) + currentCardCount);
            }
            totalScratchcards += currentCardCount;
        }
        return totalScratchcards;
    }

}
