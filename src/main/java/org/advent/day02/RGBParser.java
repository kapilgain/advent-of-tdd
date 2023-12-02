package org.advent.day02;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RGBParser {

    private static final Pattern RGB_DATA_PATTERN = Pattern.compile(
            "^\\d+ (red|green|blue)(, \\d+ (blue|red|green))*$"
    );

    public RGB parse(String data) {
        if (!RGB_DATA_PATTERN.matcher(data).matches()) {
            throw new RuntimeException("RGB data is not in the correct format: " + data);
        }

        var red = 0;
        var green = 0;
        var blue = 0;
        var colours = Arrays.stream(data.split(",")).map(String::trim).toList();
        for (var colour : colours) {
            var colourData = colour.split(" ");
            var count = Integer.parseInt(colourData[0]);
            switch (colourData[1]) {
                case "red":
                    red = count;
                    break;
                case "green":
                    green = count;
                    break;
                case "blue":
                    blue = count;
                    break;
                default:
                    throw new RuntimeException("Not a valid colour. Should be one of red, green or blue");
            }
        }

        return new RGB(red, green, blue);
    }

}
