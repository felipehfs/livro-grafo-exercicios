package br.com.felipesoftware.graph.search;

import java.util.*;

public class Path {
    private Map<String, String> path;

    public Path() {
        this.path = new HashMap<>();
    }

    void turnOn(String previous, String next) {
        this.path.put(previous, next);
    }

    public List<String> generate(String origin, String destination) {
        List<String> result = new ArrayList<>();
        String no = destination;
        while(no != origin && this.path.containsKey(no)) {
            result.add(no);
            no = this.path.get(no);
        }
        result.add(no);
        Collections.reverse(result);
        return result;
    }

}
