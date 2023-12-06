package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.LineIterator;

import java.util.*;

public class Day5 extends DayBase {
    @Override
    void solve1() {
        LineIterator it = getPuzzleLines("day5.txt");
        long lowestLocation = solve1Impl(it);
        System.out.println("The lowest location is " + lowestLocation);
    }

    long solve1Impl(LineIterator it) {
        long lowestLocation = Long.MAX_VALUE;
        String seedString = it.nextLine();
        List<Long> seeds = Arrays.stream(seedString.split(":")[1].trim().split(" ")).map(Long::parseLong).toList();
        List<Map> maps = new ArrayList<>();
        parseMaps(it, maps);
        for (Long seed : seeds) {
            Long location = getLocation(seed, maps);
            if (location < lowestLocation) {
                lowestLocation = location;
            }
        }
        return lowestLocation;
    }

    @Override
    void solve2() {
        LineIterator it = getPuzzleLines("day5.txt");
        solve2Impl(it);
    }

    void solve2Impl(LineIterator it) {
        String seedString = it.nextLine();
        List<Long> seedRanges = Arrays.stream(seedString.split(":")[1].trim().split(" ")).map(Long::parseLong).toList();
        List<Map> maps = new ArrayList<>();
        parseMaps(it, maps);
        for (int i = 0; i < seedRanges.size(); i += 2) {
            SolverThread thread = new SolverThread(seedRanges.get(i), seedRanges.get(i+1), maps);
            thread.start();
        }
    }

    private void parseMaps(LineIterator it, List<Map> maps) {
        MapType mapType = MapType.UNKNOWN;
        while (it.hasNext()) {
            String line = it.nextLine();
            if (line.contains("map")) {
                mapType = MapType.valueOf(line.split(" ")[0].toUpperCase().replaceAll("-", "_"));
            } else if (!line.isEmpty()) {
                List<Long> coordinates = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
                Map map = new Map(mapType, coordinates.get(0), coordinates.get(1), coordinates.get(2));
                maps.add(map);
            }
        }
    }

    private Long getLocation(Long seed, List<Map> maps) {
        Long location = seed;
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            Long finalLocation = location;
            Optional<Map> map = maps.stream().filter(m -> m.mapType.ordinal() == finalI).filter(m -> m.destinationStart <= finalLocation && m.destinationStart + m.range > finalLocation).findFirst();
            if (map.isPresent()) {
                location = map.get().sourceStart + (location - map.get().destinationStart);
            }
        }
        return location;
    }

    private static class Map {
        MapType mapType;
        Long sourceStart;
        Long destinationStart;
        Long range;

        public Map(MapType mapType, Long sourceStart, Long destinationStart, Long range) {
            this.mapType = mapType;
            this.sourceStart = sourceStart;
            this.destinationStart = destinationStart;
            this.range = range;
        }
    }

    private class SolverThread extends Thread {
        Long start;
        Long range;
        List<Map> maps;

        public SolverThread(Long start, Long range, List<Map> maps) {
            this.start = start;
            this.range = range;
            this.maps = maps;
        }
        @Override
        public void run() {
            long lowestLocation = Long.MAX_VALUE;
            for (Long i = 0L; i < range; i++) {
                Long location = getLocation(start + i, maps);
                if (location < lowestLocation) {
                    lowestLocation = location;
                }
            }
            System.out.println("Finished a range! Its lowest location is " + lowestLocation);
        }
    }

    private enum MapType {
        SEED_TO_SOIL, SOIL_TO_FERTILIZER, FERTILIZER_TO_WATER, WATER_TO_LIGHT,
        LIGHT_TO_TEMPERATURE, TEMPERATURE_TO_HUMIDITY, HUMIDITY_TO_LOCATION, UNKNOWN
    }

}
