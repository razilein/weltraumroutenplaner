package de.sg.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.sg.model.Edge;
import de.sg.model.Node;
import de.sg.model.Route;

class ZeigeRouteUtilsTest {

    @Test
    void testAnzeigen() {
        ZeigeRouteUtils.anzeigen(1, getRoute(), getNodes());
    }

    private List<Node> getNodes() {
        final List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            nodes.add(new Node("node_" + i));
        }
        return nodes;
    }

    private Route getRoute() {
        final List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, BigDecimal.ONE));
        edges.add(new Edge(6, 2, BigDecimal.ONE));
        edges.add(new Edge(7, 6, BigDecimal.ONE));
        edges.add(new Edge(7, 8, BigDecimal.ONE));
        return new Route(edges);
    }

}
