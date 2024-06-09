package br.com.felipesoftware;

import br.com.felipesoftware.graph.core.Digraph;
import br.com.felipesoftware.graph.core.Graph;
import br.com.felipesoftware.graph.core.Vertex;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        Digraph digraph = new Digraph();

        digraph.addVertex("RJ");
        digraph.addVertex("SP");
        digraph.addVertex("BH");
        digraph.addVertex("PT");
        digraph.addVertex("OS");
        digraph.addVertex("SV");
        digraph.addVertex("CR");
        digraph.addVertex("PA");

        digraph.connectVertices("RJ", "SP", null);
        digraph.connectVertices("RJ", "BH", null);
        digraph.connectVertices("RJ", "PT", null);
        digraph.connectVertices("RJ", "PA", null);
        digraph.connectVertices("RJ", "BH", null);
        digraph.connectVertices("SP", "BH", null);
        digraph.connectVertices("SP", "OS", null);
        digraph.connectVertices("SP", "SV", null);
        digraph.connectVertices("SP", "CR", null);
        digraph.connectVertices("SP", "PA", null);
        digraph.connectVertices("SV", "PA", null);
        digraph.connectVertices("CR", "PA", null);

        Graph tree = digraph.spanningTreeDeepSearch("PT");

        System.out.println("-- Árvore geradora via busca por profundidade usando raiz ---");
        System.out.println();
        for (Vertex v : tree.getVertices()) {
            System.out.print("Vértice " + v.getLabel() + " conectado a: ");
            List<Vertex> adjacents = tree.getAdjacents(v.getLabel());
            if (!adjacents.isEmpty()) {
                for (Vertex adj : adjacents) {
                    System.out.print(adj.getLabel() + " ");
                }
            } else {
                System.out.print("-");
            }
            System.out.println();
        }

        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.connectVertices("A", "B", 12);
        graph.connectVertices("C", "E", 10);
        graph.connectVertices("B", "D", 5);
        graph.connectVertices("D", "A", 2);
        graph.connectVertices("B", "E", 1);
        graph.connectVertices("A", "C", 7);

        System.out.println("Grafo ponderado");
        int weight = graph.getWeight("A", "C");
        System.out.println("Peso da aresta AC: " + weight);
        weight = graph.getWeight("B", "E");
        System.out.println("Peso da aresta BE: " + weight);


        Digraph complexDigraph = new Digraph();
        complexDigraph.addVertex("X");
        complexDigraph.addVertex("Y");
        complexDigraph.addVertex("Z");
        complexDigraph.addVertex("W");
        complexDigraph.addVertex("V");

        complexDigraph.connectVertices("X", "V", 44);
        complexDigraph.connectVertices("Y", "W", 37);
        complexDigraph.connectVertices("W", "Z", 38);
        complexDigraph.connectVertices("X", "V", 16);
        complexDigraph.connectVertices("V", "X", 22);
        complexDigraph.connectVertices("V", "Y", 57);

        System.out.println("Dígrafo Ponderado");
        System.out.println("Vértices: ");
        for (Vertex v : complexDigraph.getVertices()) {
            System.out.println("\t" + v.getLabel());
        }

        System.out.println();
        System.out.println("Arestas:");
        for(Vertex v : complexDigraph.getVertices()) {
            for(Vertex adj : complexDigraph.getAdjacents(v.getLabel())) {
                System.out.println("\t" + v.getLabel() + adj.getLabel() + ":" + complexDigraph.getWeight(v.getLabel(), adj.getLabel()));
            }
        }
    }
}