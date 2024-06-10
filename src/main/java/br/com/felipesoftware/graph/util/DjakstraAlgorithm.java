package br.com.felipesoftware.graph.util;

import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DjakstraAlgorithm {
    private static DjakstraAlgorithm djakstraAlgorithm;
    private  DjakstraAlgorithm() {}

    public static DjakstraAlgorithm getInstance() {
        if (djakstraAlgorithm == null) {
            djakstraAlgorithm = new DjakstraAlgorithm();
        }

        return djakstraAlgorithm;
    }

    public Map<String, Info> process(String origin, String destination, Graph graph) {
        try {
            // verfifo se ambos os vértices existem
            graph.getVertex(origin);
            graph.getVertex(destination);
        } catch (Exception e) {
            throw e;
        }

        Map<String, Info> infoVertex = new HashMap<>();
        infoVertex.put(origin, new Info(0, null));

        Set<String> toVisit = new HashSet<>();
        toVisit.add(origin);

        while (toVisit.size() > 0) {
            String bestVertice = null;
            int minorDistance = Integer.MAX_VALUE;
            for (String v : toVisit) {
                Info info = infoVertex.get(v);
                if (info.distance < minorDistance) {
                    bestVertice = v;
                    minorDistance = info.distance;
                }
            }

            if (bestVertice.equals(destination)) {
                break;
            }

            toVisit.remove(bestVertice);

            for (Vertex neighbor: graph.getAdjacents(bestVertice)) {
                String label = neighbor.getLabel();
                int distance = minorDistance + graph.getWeight(bestVertice, label);
                if (infoVertex.containsKey(label)) {
                    Info info = infoVertex.get(label);
                    if (distance < info.distance) {
                        info.distance = distance;
                        info.previous = graph.getVertex(bestVertice);
                    }
                } else {
                    infoVertex.put(label, new Info(distance, graph.getVertex(bestVertice)));
                    toVisit.add(label);
                }
            }
        }
        return infoVertex;
    }

    public class Info {
        public int distance;
        public Vertex previous;

        Info(int distance, Vertex previous) {
            this.distance = distance;
            this.previous = previous;
        }
    }

}
