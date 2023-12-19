package org.advent.day17;

import org.advent.utils.Pointer;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public record Crucible(int heatLoss, Pointer pointer, int stepsSinceLastTurn) implements Comparable<Crucible> {

    @Override
    public int compareTo(Crucible o) {
        return Integer.compare(heatLoss, o.heatLoss);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this, "heatLoss");
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj, "heatLoss");
    }

}
