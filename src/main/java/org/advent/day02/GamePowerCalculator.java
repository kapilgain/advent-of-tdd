package org.advent.day02;

import java.util.List;

public class GamePowerCalculator {

    public int calculatePower(Game game) {
        var maxRedOptional = game.getReveals().stream().map(RGB::red).mapToInt(Integer::intValue).max();
        var maxRed = 0;
        if (maxRedOptional.isPresent()) {
            maxRed = maxRedOptional.getAsInt();
        }

        var maxGreenOptional = game.getReveals().stream().map(RGB::green).mapToInt(Integer::intValue).max();
        var maxGreen = 0;
        if (maxGreenOptional.isPresent()) {
            maxGreen = maxGreenOptional.getAsInt();
        }

        var maxBlueOptional = game.getReveals().stream().map(RGB::blue).mapToInt(Integer::intValue).max();
        var maxBlue = 0;
        if (maxBlueOptional.isPresent()) {
            maxBlue = maxBlueOptional.getAsInt();
        }

        return maxRed * maxGreen * maxBlue;
    }

    public int sumPowers(List<Game> games) {
        return games.stream()
                .map(game -> new GamePowerCalculator().calculatePower(game))
                .mapToInt(Integer::intValue)
                .sum();
    }

}
