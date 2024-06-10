package br.com.felipesoftware.graph.util;

import br.com.felipesoftware.graph.core.Digraph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.HashMap;
import java.util.Map;

public class FloydWarshallAlgorithm {
    private static FloydWarshallAlgorithm floydWarshallAlgorithm;

    private FloydWarshallAlgorithm() {}

    public static FloydWarshallAlgorithm getInstance() {
        if (floydWarshallAlgorithm == null) {
            floydWarshallAlgorithm = new FloydWarshallAlgorithm();
        }
        return floydWarshallAlgorithm;
    }

    public Map<String, Map<String, Info>> process(Digraph digraph) {
        Map<String, Map<String, Info>> matrix = new HashMap<>();

        for (Vertex u: digraph.getVertices()) {
            Map<String, Info> row = new HashMap<>();
            matrix.put(u.getLabel(), row);

            for (Vertex v: digraph.getVertices()) {
                int weight = digraph.getWeight(u.getLabel(), v.getLabel());
                int value = weight == 0 ? Integer.MAX_VALUE : weight;
                Info info = new Info();
                info.byWhatVertex = v;
                info.distance = u.getLabel().equalsIgnoreCase(v.getLabel())? 0 : value;
                row.put(v.getLabel(), info);
            }
        }

        for (Vertex k : digraph.getVertices()) {
            Map<String, Info> rowK = matrix.get(k.getLabel());
            for (Vertex u : digraph.getVertices()) {
                Map<String, Info> rowU = matrix.get(u.getLabel());
                Info uk = rowU.get(k.getLabel());

                for (Vertex v : digraph.getVertices()) {
                    Info kv = rowK.get(v.getLabel());
                    int sum = uk.distance == Integer.MAX_VALUE || kv.distance == Integer.MAX_VALUE ? Integer.MAX_VALUE : uk.distance + kv.distance;
                    if (sum < rowU.get(v.getLabel()).distance) {
                        Info info = new Info();
                        info.byWhatVertex = uk.byWhatVertex;
                        info.distance = sum;
                        rowU.put(v.getLabel(), info);
                    }
                }
            }
        }

        return matrix;
    }

    public class Info {
        public Vertex byWhatVertex;
        public int distance;
    }
}
