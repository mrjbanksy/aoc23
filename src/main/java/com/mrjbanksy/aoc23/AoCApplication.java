package com.mrjbanksy.aoc23;

import com.mrjbanksy.aoc23.day.DayBase;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class AoCApplication implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        if (args.length != 2) {
            System.out.println("Invalid arguments passed. Usage: ./mvnw quarkus:dev -Dquarkus.args='<day> <puzzle number>");
            return 1;
        }
        String day = args[0];
        int puzzle = Integer.parseInt(args[1]);

        Class<?> dayClass = Class.forName("com.mrjbanksy.aoc23.day.Day" + day);
        Object dayClassInstance = dayClass.getDeclaredConstructor().newInstance();
        ((DayBase) dayClassInstance).solve(puzzle);

        return 0;
    }


}
