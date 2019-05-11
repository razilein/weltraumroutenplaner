package de.sg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.sg.model.Graph;
import de.sg.model.Route;
import de.sg.util.BerechneRouteUtils;
import de.sg.util.GraphUtils;
import de.sg.util.ZeigeRouteUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Start {

    public static void main(final String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            final Graph graphs = GraphUtils.getGraphs();
            final int start = readEingabe(br, graphs, "Startplaneten");
            final int ziel = readEingabe(br, graphs, "Zielplaneten");

            log.info("Bitte haben Sie einen Moment Geduld. Die schnellste Route wird berechnet.");
            final Route route = BerechneRouteUtils.findeKuerzesteRoute(graphs, start, ziel);
            ZeigeRouteUtils.anzeigen(start, route, graphs.getNodes());
        } catch (final IOException e) {
            log.error("Die Route kann nicht berechnet werden! Sie sind auf sich selbst angewiesen! Viel Glück! ", e.getMessage(), e);
        }
    }

    private static int readEingabe(final BufferedReader br, final Graph graphs, final String bezeichnung) throws IOException {
        String eingabePlanet = "";
        int index = -1;
        while (eingabePlanet.length() == 0 || index == -1) {
            log.info("Bitte geben Sie den Namen des {} an: ", bezeichnung);
            eingabePlanet = br.readLine();
            index = GraphUtils.getIndexOfGraphByLabel(graphs.getNodes(), eingabePlanet);
            if (index == -1) {
                log.info("Der angebene Planet '" + eingabePlanet + "' kann nicht gefunden werden.");
            }
        }
        return index;
    }

}
