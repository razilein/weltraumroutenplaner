package de.sg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.sg.model.Edge;
import de.sg.model.Graph;
import de.sg.model.Route;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class BerechneRouteUtils {

    public static Route findeKuerzesteRoute(final Graph graph, final int start, final int ziel) {
        final List<Route> routen = berechneRouten(graph.getEdges(), start, ziel);
        return findeKuerzesteRoute(routen);
    }

    private static List<Route> berechneRouten(final List<Edge> edges, final int start, final int ziel) {
        final List<Route> routen = new ArrayList<>();
        berechneRouten(edges, routen, new ArrayList<>(), start, ziel);
        return routen;
    }

    private static void berechneRouten(final List<Edge> edges, final List<Route> routen, final List<Edge> aktuelleRoute, final int index,
            final int ziel) {
        final List<Edge> nextEdges = edges.stream().filter(edge -> hasSourceOrTargetIndexAndNotAlreadyUsed(aktuelleRoute, index, edge))
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

                if (!routen.isEmpty() && istRouteLaengerAlsBisherErmittelteRoute(routen, newRoute)) {
                    log.debug("Route ist bereits länger als ermittelte Zielroute. Routenberechnung für diese Route wird abgebrochen.");
                    break;
                }

                final int nextIndex = nextEdge.getTarget() == index ? nextEdge.getSource() : nextEdge.getTarget();
                log.debug("Neuer Knotenpunkt gefunden. Von '{}' nach '{}'", index, nextIndex);
                berechneRouten(edges, routen, newRoute, nextIndex, ziel);
            }
        }
    }

    private static boolean istRouteLaengerAlsBisherErmittelteRoute(final List<Route> routen, final List<Edge> newRoute) {
        return new Route(newRoute).getSumCost().compareTo(findeKuerzesteRoute(routen).getSumCost()) > 0;
    }

    private static boolean hasSourceOrTargetIndexAndNotAlreadyUsed(final List<Edge> aktuelleRoute, final int index, final Edge edge) {
        return (edge.getSource() == index || edge.getTarget() == index) && !aktuelleRoute.contains(edge);
    }

    private static Route findeKuerzesteRoute(final List<Route> routen) {
        return Collections.min(routen, Comparator.comparing(Route::getSumCost));
    }

}
