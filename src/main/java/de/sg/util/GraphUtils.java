package de.sg.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sg.model.Graph;
import de.sg.model.Node;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GraphUtils {

    public static Graph getGraphs() throws IOException {
        final File datei = getGraphDatei();
        return new ObjectMapper().readValue(datei, Graph.class);
    }

    public static int getIndexOfGraphByLabel(final List<Node> nodes, final String label) {
        return nodes.stream().map(Node::getLabel).collect(Collectors.toList()).indexOf(label);
    }

    private static File getGraphDatei() throws IOException {
        final URL url = new URL("https://www.get-in-it.de/imgs/it/codingCompetition/graph/generatedGraph.json");
        final String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        final File file = new File(FileUtils.getTempDirectory(), fileSuffix + "_graph.json");
        FileUtils.copyURLToFile(url, file);
        return file;
    }

}
