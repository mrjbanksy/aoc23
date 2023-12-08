package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day7.txt");
        long totalWinnings = solve1Impl(it);
        System.out.println("The total winnings are " + totalWinnings);
    }

    long solve1Impl(LineIterator it) {
        List<Hand> hands = new ArrayList<>();
        while (it.hasNext()) {
            String line = it.nextLine();
            Hand hand = new Hand(line, 1);
            hands.add(hand);
        }
        Collections.sort(hands);
        long totalWinnings = 0;
        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += Math.multiplyExact(i + 1, hands.get(i).bid);
        }
        return totalWinnings;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day7.txt");
        long totalWinnings = solve2Impl(it);
        System.out.println("The total winnings are " + totalWinnings);
    }

    long solve2Impl(LineIterator it) {
        List<Hand> hands = new ArrayList<>();
        while (it.hasNext()) {
            String line = it.nextLine();
            Hand hand = new Hand(line, 2);
            hands.add(hand);
        }
        Collections.sort(hands);
        long totalWinnings = 0;
        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += Math.multiplyExact(i + 1, hands.get(i).bid);
        }
        return totalWinnings;
    }

    private static class Hand implements Comparable<Hand> {
        List<CardType> cards;
        Map<CardType, Integer> cardMap;

        HandType type;

        int bid;

        public Hand(String line, int type) {
            cards = new ArrayList<>();
            cardMap = new HashMap<>();

            String cardString = line.split(" ")[0];
            this.cards.add(parseCard(cardString.charAt(0), type));
            this.cards.add(parseCard(cardString.charAt(1), type));
            this.cards.add(parseCard(cardString.charAt(2), type));
            this.cards.add(parseCard(cardString.charAt(3), type));
            this.cards.add(parseCard(cardString.charAt(4), type));

            for (CardType card : cards) {
                cardMap.put(card, cardMap.getOrDefault(card, 0) + 1);
            }

            this.bid = Integer.parseInt(line.split(" ")[1]);
            this.type = getHandType();
        }

        private CardType parseCard(char card, int type) {
            return switch (card) {
                case 'A' -> CardType.ACE;
                case 'K' -> CardType.KING;
                case 'Q' -> CardType.QUEEN;
                case 'J' -> {
                    if (type == 1) {
                        yield CardType.JACK;
                    } else {
                        yield CardType.JOKER;
                    }
                }
                case 'T' -> CardType.TEN;
                case '9' -> CardType.NINE;
                case '8' -> CardType.EIGHT;
                case '7' -> CardType.SEVEN;
                case '6' -> CardType.SIX;
                case '5' -> CardType.FIVE;
                case '4' -> CardType.FOUR;
                case '3' -> CardType.THREE;
                case '2' -> CardType.TWO;
                default -> throw new RuntimeException("Invalid card type!");
            };
        }

        private HandType getHandType() {
            int jokerCount = cardMap.getOrDefault(CardType.JOKER, 0);
            Map<CardType, Integer> filteredCardMap = cardMap.entrySet().stream().filter(c -> c.getKey() != CardType.JOKER).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            List<Integer> sortedCardCount = filteredCardMap.values().stream().sorted().toList();
            if (jokerCount == 5 || filteredCardMap.values().stream().anyMatch(v -> v + jokerCount == 5)) {
                return HandType.FIVE_OF_A_KIND;
            } else if (filteredCardMap.values().stream().anyMatch(v -> v + jokerCount == 4)) {
                return HandType.FOUR_OF_A_KIND;
            } else if (sortedCardCount.get(sortedCardCount.size() - 1) + sortedCardCount.get(sortedCardCount.size() - 2) + jokerCount == 5) {
                return HandType.FULL_HOUSE;
            } else if (filteredCardMap.values().stream().anyMatch(v -> v + jokerCount == 3)) {
                return HandType.THREE_OF_A_KIND;
            } else if (sortedCardCount.get(sortedCardCount.size() - 1) + sortedCardCount.get(sortedCardCount.size() - 2) + jokerCount == 4) {
                return HandType.TWO_PAIR;
            } else if (filteredCardMap.values().stream().anyMatch(v -> v + jokerCount == 2)) {
                return HandType.ONE_PAIR;
            } else {
                return HandType.HIGH_CARD;
            }
        }

        @Override
        public int compareTo(Hand hand2) {
            if (this.type.ordinal() < hand2.type.ordinal()) {
                return 1;
            } else if (this.type.ordinal() > hand2.type.ordinal()) {
                return -1;
            } else {
                for (int i = 0; i < 5; i++) {
                    if (this.cards.get(i).ordinal() < hand2.cards.get(i).ordinal()) {
                        return 1;
                    } else if (this.cards.get(i).ordinal() > hand2.cards.get(i).ordinal()) {
                        return -1;
                    }
                }
                return 0;
            }
        }
    }

    private enum CardType {
        ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, JOKER
    }

    private enum HandType {
        FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, THREE_OF_A_KIND, TWO_PAIR, ONE_PAIR, HIGH_CARD
    }

}