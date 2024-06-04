package br.com.felipesoftware;

import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;
import br.com.felipesoftware.graph.search.BreathFirstSearch;
import br.com.felipesoftware.graph.search.DeepSearch;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        usingSearch();
    }

    private static void usingSearch() throws Exception{
        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

        graph.connectVertices("A", "B");
        graph.connectVertices("A", "C");
        graph.connectVertices("A", "D");
        graph.connectVertices("B", "F");
        graph.connectVertices("B", "I");
        graph.connectVertices("D", "E");
        graph.connectVertices("D", "I");
        graph.connectVertices("D", "G");
        graph.connectVertices("I", "A");
        graph.connectVertices("I", "D");
        graph.connectVertices("I", "C");
        graph.connectVertices("I", "H");
        graph.connectVertices("E", "A");

        List<String> path = DeepSearch.getInstance().search(graph, "D", "H");
        System.out.print("Caminho feito por uma busca em profundidade: ");

        for(String passo: path) {
            System.out.print(passo + " ");
        }

        path = BreathFirstSearch.getInstance().search(graph, "B", "G");
        System.out.println();
        System.out.print("Caminho feito por uma busca em largura: ");
        for (String passo: path) {
            System.out.print(passo + " ");
        }

        Graph tree = graph.spaningTreeByDeepSearch();
        System.out.println();
        System.out.println("--- Árvore geradora ---");
        System.out.println("Vértices");
        for (Vertex v : tree.getVertices()) {
            System.out.println("\t" + v.getLabel());
        }

        System.out.println("Arestas");
        for (Vertex v: tree.getVertices()) {
            for (Vertex adj : tree.getAdjacents(v.getLabel())) {
                System.out.println("\t" + v.getLabel() + adj.getLabel());
            }
        }
     }

    private static void secondChapterLogic() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.connectVertices("A", "B");
        graph.connectVertices("A", "C");
        graph.connectVertices("A", "D");

        System.out.println("Grau do vértice A: " + graph.getVertex("A").getGrau());
        System.out.println("Grau do vértice D: " + graph.getVertex("D").getGrau());
        System.out.println("Grau do vértice C: " + graph.getVertex("C").getGrau());

        System.out.println();
        System.out.println("O vértice A possui as seguintes adjacências:");
        List<Vertex> adjacents = graph.getAdjacents("A");

        for(Vertex vertex: adjacents) {
            System.out.print(vertex.getLabel() + " ");
        }

        System.out.println();
        System.out.print("O vértice C possui as seguintes adjacências");
        adjacents = graph.getAdjacents("C");
        for(Vertex vertex: adjacents) {
            System.out.print(vertex.getLabel() + " ");
        }
    }

    private static void firstChapterLogic() throws Exception {
        Graph graph = new Graph();
        graph.addVertex("RJ");
        graph.addVertex("SP");
        graph.addVertex("BH");
        graph.addVertex("PT");
        graph.addVertex("OS");
        graph.addVertex("SV");
        graph.addVertex("CR");
        graph.addVertex("PA");

        System.out.println("O grafo G possui os seguintes vértices");

        for(Vertex vertex : graph.getVertices()) {
            System.out.println("-Vértices "+ vertex.getLabel());
        }
    }
}