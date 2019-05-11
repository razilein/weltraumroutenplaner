package de.sg.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Graph {

    private List<Node> nodes;

    private List<Edge> edges;
}
