package de.sg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.sg.model.Edge;
import de.sg.model.Route;

class BerechneRouteServiceTest {

    private BerechneRouteService service;

    @BeforeEach
    void setUp() {
        service = new BerechneRouteService(getEdges(), 1, 10);
    }

    private List<Edge> getEdges() {
        final List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, BigDecimal.ONE));
        edges.add(new Edge(2, 4, BigDecimal.ONE));
        edges.add(new Edge(1, 5, BigDecimal.ONE));
        edges.add(new Edge(1, 7, BigDecimal.ONE));
        edges.add(new Edge(2, 5, BigDecimal.ONE));
        edges.add(new Edge(6, 3, BigDecimal.ONE));
        edges.add(new Edge(8, 3, BigDecimal.ONE));
        edges.add(new Edge(8, 9, BigDecimal.ONE));
        edges.add(new Edge(9, 10, BigDecimal.ONE));
        edges.add(new Edge(7, 8, BigDecimal.ONE));
        edges.add(new Edge(1, 3, BigDecimal.ONE));
        edges.add(new Edge(4, 3, BigDecimal.ONE));
        edges.add(new Edge(4, 5, BigDecimal.ONE));
        edges.add(new Edge(6, 5, BigDecimal.ONE));
        edges.add(new Edge(6, 7, BigDecimal.ONE));
        return edges;
    }

    @Test
    void testFindeKurzesteRoute() {
        final Route route = service.findeKuerzesteRoute();
        assertEquals(new BigDecimal("4"), route.getSumCost());
        final List<Edge> routeEdges = route.getEdges();
        assertEquals(4, routeEdges.size());

        final List<Edge> edges = getEdges();
        assertEquals(edges.get(3), routeEdges.get(0));
        assertEquals(edges.get(9), routeEdges.get(1));
        assertEquals(edges.get(7), routeEdges.get(2));
        assertEquals(edges.get(8), routeEdges.get(3));
    }

}
