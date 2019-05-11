package de.sg;

import java.io.IOException;

import de.sg.model.Graph;
import de.sg.model.Route;
import de.sg.util.BerechneRouteUtils;
import de.sg.util.GraphUtils;
import de.sg.util.ZeigeRouteUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Start {

    private static final String STARTPLANET = "Erde";

    private static final String ZIELPLANET = "b3-r7-r4nd7";

    public static void main(final String[] args) {
        try {
            final Graph graphs = GraphUtils.getGraphs();
            final int start = GraphUtils.getIndexOfGraphByLabel(graphs.getNodes(), STARTPLANET);

            if (start == -1) {
                throw new IllegalStateException("Der angebene Startplanet '" + STARTPLANET + "' kann nicht gefunden werden.");
            }

            final int ziel = GraphUtils.getIndexOfGraphByLabel(graphs.getNodes(), ZIELPLANET);
            if (ziel == -1) {
                throw new IllegalStateException("Der angebene Zielplanet '" + STARTPLANET + "' kann nicht gefunden werden.");
            }

            log.info("Bitte haben Sie einen Moment Geduld. Die schnellste Route von Planet '{}' nach Planet '{}' wird berechnet.",
                    STARTPLANET, ZIELPLANET);
            final Route route = BerechneRouteUtils.findeKuerzesteRoute(graphs, start, ziel);
            ZeigeRouteUtils.anzeigen(start, route, graphs.getNodes());
        } catch (final IOException e) {
            log.error("Die Route kann nicht berchnet werden! Sie sind auf sich selbst angewiesen! Viel Glück! ", e.getMessage(), e);
        }
    }

}
