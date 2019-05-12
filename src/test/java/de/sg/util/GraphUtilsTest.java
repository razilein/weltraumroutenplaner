package de.sg.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.sg.model.Graph;
import de.sg.model.Node;

class GraphUtilsTest {

    @Test
    void testGetGraphs() throws IOException {
        final Graph graphs = GraphUtils.getGraphs();
        assertThat(graphs.getEdges().size(), greaterThan(0));
        assertThat(graphs.getNodes().size(), greaterThan(0));
    }

    @Test
    void testGetIndexOfGraphByLabel() {
        assertEquals(10, GraphUtils.getIndexOfGraphByLabel(getNodes(), "node_10"));
        assertEquals(999, GraphUtils.getIndexOfGraphByLabel(getNodes(), "node_999"));
        assertEquals(-1, GraphUtils.getIndexOfGraphByLabel(getNodes(), "node_1001"));
    }

    private List<Node> getNodes() {
        final List<Node> nodes = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            nodes.add(new Node("node_" + i));
        }
        return nodes;
    }

}
