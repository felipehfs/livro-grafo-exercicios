package br.com.felipesoftware.graph.util;

import br.com.felipesoftware.graph.core.Digraph;
import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrimAlgorithm {
    private static  PrimAlgorithm primAlgorithm;

    private Map<String, String> candidates;
    private Set<String> toConnect;


    private PrimAlgorithm() {}

    public static PrimAlgorithm getInstance() {
        if (primAlgorithm == null) {
            primAlgorithm = new PrimAlgorithm();
        }

        return  primAlgorithm;
    }

    public Digraph process(String root, Graph graph) throws Exception {
        toConnect = new HashSet<>();
        for (Vertex vertex : graph.getVertices()) {
            toConnect.add(vertex.getLabel());
        }

        Digraph mst = new Digraph();
        mst.addVertex(root);
        toConnect.remove(root);

        candidates = new HashMap<>();
        updateCandidates(graph, root);

        while (toConnect.size() > 0) {
            String bestU = null;
            String bestV = null;

            int minorDistance = Integer.MAX_VALUE;
            for (String u : candidates.keySet()) {
                String v = candidates.get(u);
                int weight = graph.getWeight(u, v);
                if (weight < minorDistance) {
                    bestU = u;
                    bestV = v;
                    minorDistance = weight;
                }
            }

            if (minorDistance == Integer.MAX_VALUE) {
                break;
            }

            mst.addVertex(bestV);
            mst.connectVertices(bestU, bestV, minorDistance);
            toConnect.remove(bestV);
            updateCandidates(graph, bestU);
            updateCandidates(graph, bestV);
        }

        return mst;
    }

    private void updateCandidates(Graph graph, String vertex) {
        int minorDistance = Integer.MAX_VALUE;
        String bestNear = null;
        for (Vertex adj : graph.getAdjacents(vertex)) {
            int weight = graph.getWeight(vertex, adj.getLabel());
            if (toConnect.contains(adj.getLabel()) &&  weight < minorDistance) {
                minorDistance = weight;
                bestNear = adj.getLabel();
            }
        }

        if (bestNear != null) {
            candidates.put(vertex, bestNear);
        } else {
            candidates.remove(vertex);
        }
    }
}
