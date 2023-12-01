package com.mrjbanksy.aoc23.day;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class DayBase {

    public void solve(int puzzle) {
        if (puzzle == 1) {
            solve1();
        } else {
            solve2();
        }
    }

    public LineIterator getPuzzleLines(String fileName) {
        URL resource = getClass().getClassLoader().getResource("input/" + fileName);
        try {
            assert resource != null;
            return FileUtils.lineIterator(new File(resource.toURI()), "UTF-8");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void solve1();

    abstract void solve2();
}
