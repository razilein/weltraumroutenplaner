package de.sg.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Edge {

    private int source;

    private int target;

    private BigDecimal cost;

}
