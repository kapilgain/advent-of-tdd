package org.advent.day19;

public record Part(int x, int m, int a, int s) {

    public int calculateChristmasRating() {
        return x + m + a + s; // 🎄
    }

    public static Part parse(String line) {
        var values = line.substring(1, line.length() - 1).split(",");
        var x = Integer.parseInt(values[0].split("=")[1]);
        var m = Integer.parseInt(values[1].split("=")[1]);
        var a = Integer.parseInt(values[2].split("=")[1]);
        var s = Integer.parseInt(values[3].split("=")[1]);
        return new Part(x, m, a, s);
    }

}