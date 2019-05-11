package de.sg.util;

import java.util.List;

import de.sg.model.Edge;
import de.sg.model.Node;
import de.sg.model.Route;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ZeigeRouteUtils {

    public static void anzeigen(final int start, final Route route, final List<Node> nodes) {
        log.info("Folgende Route wurde ermittelt: ");
        int index = start;
        for (final Edge edge : route.getEdges()) {
            final int from = edge.getSource() == index ? edge.getSource() : edge.getTarget();
            final int to = edge.getSource() == index ? edge.getTarget() : edge.getSource();
            index = to;
            log.info("{} -> {} (Entfernung: {})", nodes.get(from).getLabel(), nodes.get(to).getLabel(), edge.getCost());
        }
        log.debug("Gesamte Entfernung: {}", route.getSumCost());
    }

}
