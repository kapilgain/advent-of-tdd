package org.advent.day25;

import io.vavr.Tuple;
import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;
import java.util.List;

public class Day25Part1 {

    public Number solve(List<String> lines) {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        lines.stream()
                .map(line -> line.trim().split(":\\s+"))
                .map(parts -> {
                    var left = parts[0].trim();
                    var right = Arrays.stream(parts[1].trim().split("\\s+")).toList();
                    return Tuple.of(left, right);
                })
                .forEach(tuple -> {
                    graph.addVertex(tuple._1);
                    tuple._2.forEach(right -> {
                        graph.addVertex(right);
                        graph.addEdge(tuple._1, right);
                    });
                });

        var firsPartition = new StoerWagnerMinimumCut<>(graph).minCut().size();
        var secondPartition = graph.vertexSet().size() - firsPartition;
        return firsPartition * secondPartition;
    }

}
