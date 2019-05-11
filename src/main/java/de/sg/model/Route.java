package de.sg.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Route {

    private final List<Edge> edges;

    public BigDecimal getSumCost() {
        return edges.stream().map(Edge::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
