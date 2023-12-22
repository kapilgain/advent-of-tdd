package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

@Getter
public class SparseGrid<T> {
    private final Map<Tuple2<? extends Number, ? extends Number>, T> grid = new HashMap<>();

    public void set(Tuple2<? extends Number, ? extends Number> rowCol, T value) {
        grid.put(rowCol, value);
    }

    public T get(Tuple2<? extends Number, ? extends Number> rowCol) {
        return grid.get(rowCol);
    }

    public void print(PrintStream out) {
        var minRow = grid.keySet().stream().mapToInt(t -> t._1.intValue()).min().orElse(0);
        var maxRow = grid.keySet().stream().mapToInt(t -> t._1.intValue()).max().orElse(0);
        var minCol = grid.keySet().stream().mapToInt(t -> t._2.intValue()).min().orElse(0);
        var maxCol = grid.keySet().stream().mapToInt(t -> t._2.intValue()).max().orElse(0);

        for (var r = minRow; r <= maxRow; r++) {
            for (var c = minCol; c <= maxCol; c++) {
                var value = grid.get(Tuple.of(r, c));
                if (value == null) {
                    out.print(GridUtils.EMPTY);
                } else {
                    out.print(value);
                }
            }
            out.println();
        }
    }

    @Override
    public String toString() {
        var out = new ByteArrayOutputStream();
        print(new PrintStream(out));
        return out.toString().trim();
    }

}
