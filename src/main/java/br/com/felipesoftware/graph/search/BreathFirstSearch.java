package br.com.felipesoftware.graph.search;

import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreathFirstSearch {
    private static BreathFirstSearch instance;

    private BreathFirstSearch() {}

    public static BreathFirstSearch getInstance() {
        if (instance == null) {
            instance = new BreathFirstSearch();
        }
        return instance;
    }

    public List<String> search(Graph graph, String origin, String destination) {
        Queue<String> queue = new LinkedList<String>();
        LinkedHashSet<String> verticesVisited = new LinkedHashSet<>();
        Path path = new Path();

        queue.add(origin);

        while(!queue.isEmpty()) {
            String v = queue.poll();
            if (v.equals(destination)) {
                break;
            }
            for (Vertex u : graph.getAdjacents(v)) {
                String label = u.getLabel();
                if (!verticesVisited.contains(label)) {
                    verticesVisited.add(label);
                    path.turnOn(label, v);
                    queue.add(label);
                }
            }
        }

        return path.generate(origin, destination);
    }
}
