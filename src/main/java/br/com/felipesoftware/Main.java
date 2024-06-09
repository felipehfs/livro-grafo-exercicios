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
    }
}