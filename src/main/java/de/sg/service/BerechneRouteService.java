package de.sg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.sg.model.Edge;
import de.sg.model.Graph;
import de.sg.model.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BerechneRouteService {

    final List<Route> routen;

    public BerechneRouteService() {
        routen = new ArrayList<>();
    }

    public Route findeKuerzesteRoute(final Graph graph, final int start, final int ziel) {
        berechneRouten(graph.getEdges(), start, ziel);
        return findeKuerzesteRoute(routen);
    }

    private void berechneRouten(final List<Edge> edges, final int start, final int ziel) {
        berechneRouten(edges, new ArrayList<>(), start, ziel);
    }

    private void berechneRouten(final List<Edge> edges, final List<Edge> aktuelleRoute, final int index, final int ziel) {
        final List<Edge> nextEdges = edges.stream().filter(edge -> checkIsAllowedEdge(aktuelleRoute, index, edge))
                .collect(Collectors.toList());

        final Optional<Edge> destinationEdge = nextEdges.stream().filter(edge -> edge.getTarget() == ziel).findFirst();
        if (destinationEdge.isPresent()) {
            log.debug("Zielroute nach '{}' gefunden.", ziel);
            final List<Edge> newRoute = new ArrayList<>();
            newRoute.addAll(aktuelleRoute);
            newRoute.add(destinationEdge.get());
            routen.add(new Route(newRoute));
        } else {
            for (final Edge nextEdge : nextEdges) {
                final List<Edge> newRoute = new ArrayList<>();
                newRoute.addAll(aktuelleRoute);
                newRoute.add(nextEdge);

                final int nextIndex = nextEdge.getTarget() == index ? nextEdge.getSource() : nextEdge.getTarget();
                log.debug("Neuer Knotenpunkt gefunden. Von '{}' nach '{}'", index, nextIndex);
                berechneRouten(edges, newRoute, nextIndex, ziel);
            }
        }
    }

    private static boolean istRouteLaengerAlsBisherErmittelteRoute(final List<Route> routen, final List<Edge> newRoute) {
        return new Route(newRoute).getSumCost().compareTo(findeKuerzesteRoute(routen).getSumCost()) > 0;
    }

    private boolean checkIsAllowedEdge(final List<Edge> aktuelleRoute, final int index, final Edge edge) {
        boolean result = edge.getSource() == index || edge.getTarget() == index;
        result = result && !aktuelleRoute.contains(edge);
        if (result && !routen.isEmpty()) {
            final List<Edge> newRoute = new ArrayList<>();
            newRoute.addAll(aktuelleRoute);
            newRoute.add(edge);
            if (istRouteLaengerAlsBisherErmittelteRoute(routen, newRoute)) {
                log.debug("Route ist bereits länger als kürzeste ermittelte Zielroute. Routenberechnung für diese Route wird abgebrochen.");
                result = false;
            }
        }
        return result;
    }

    private static Route findeKuerzesteRoute(final List<Route> routen) {
        return Collections.min(routen, Comparator.comparing(Route::getSumCost));
    }

}
