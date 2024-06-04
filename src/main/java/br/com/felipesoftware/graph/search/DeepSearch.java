package br.com.felipesoftware.graph.search;

import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

public class DeepSearch {
    private static DeepSearch instance;

    private DeepSearch() {}

    public static DeepSearch getInstance() {
        if (instance == null) {
            instance = new DeepSearch();
        }
        return instance;
    }

    public List<String> search(Graph graph, String origin, String destination) {
        Stack<String> stack = new Stack<>();
        LinkedHashSet<String> verticesVisited = new LinkedHashSet<>();
        Path path = new Path();
        stack.push(origin);

        while (!stack.empty()) {
            String v = stack.pop();
            if (v.equals(destination)) {
                break;
            }

            for (Vertex u: graph.getAdjacents(v)) {
                String label = u.getLabel();
                if (!verticesVisited.contains(label)) {
                    verticesVisited.add(label);
                    path.turnOn(label, v);
                    stack.push(label);
                }
            }
        }

        return path.generate(origin, destination);
    }
}
