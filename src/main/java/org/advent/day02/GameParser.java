package org.advent.day02;

import java.util.Arrays;
import java.util.regex.Pattern;

public class GameParser {

    private static final Pattern GAME_DATA_PATTERN = Pattern.compile(
            "^Game \\d+: (\\d+ (blue|red|green)(, )?)+(; (\\d+ (blue|red|green)(, )?)+)*$"
    );

    public Game parse(String line) {
        if (!GAME_DATA_PATTERN.matcher(line).matches()) {
            throw new RuntimeException("Game data is not in the correct format: " + line);
        }

        var gameData = line.split(":");
        var gameId = Integer.parseInt(gameData[0].trim().replace("Game ", ""));
        var game = new Game(gameId);
        var revealsData = gameData[1];
        var reveals = Arrays.stream(revealsData.split(";"))
                .map(String::trim)
                .map(data -> new RGBParser().parse(data))
                .toList();

        for (var reveal : reveals) {
            game.addReveal(reveal);
        }

        return game;
    }

}
