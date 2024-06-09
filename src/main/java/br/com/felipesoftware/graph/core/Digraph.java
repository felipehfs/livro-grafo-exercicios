package br.com.felipesoftware.graph.core;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Digraph extends Graph {

    @Override
    public void connectVertices(String initialVertexLabel, String finalVertexLabel, Integer weight) throws Exception {
        if (!super.existsVertice(initialVertexLabel) || !super.existsVertice(finalVertexLabel)) {
            throw new Exception("Para adicionar uma aresta ambos os vértices devem existir.");
        }

        Map<String, Integer> labelIndexes = super.getLabelsIndexes();
        super.createAdjacentMatrix();
        AdjacentMatrix adjacentMatrix = getAdjacentMatrix();
        int initialVertexIndex = labelIndexes.get(initialVertexLabel);
        int finalVertexIndex = labelIndexes.get(finalVertexLabel);
        adjacentMatrix.addDirectionalEdge(initialVertexIndex, finalVertexIndex, weight);
    }

    public Graph spanningTreeDeepSearch() throws Exception {
        String root = super.getVertices().get(0).getLabel();
        return this.spanningTreeDeepSearch(root);
    }

    public Graph spanningTreeDeepSearch(String root) throws Exception {
        LinkedHashSet<String> toVisit = new LinkedHashSet<>();
        Digraph tree = new Digraph();
        List<Vertex> vertices = this.getVertices();
        AdjacentMatrix adjacentMatrix  = super.getAdjacentMatrix();
        Map<String, Integer> labelsIndexes = super.getLabelsIndexes();
        int rootIndex = labelsIndexes.get(root);

        for (Vertex v: vertices) {
            toVisit.add(v.getLabel());
        }
        if (root == null) {
            root = vertices.get(0).getLabel();
        }

        toVisit.remove(root);
        tree.addVertex(root);
        this.visit(root, toVisit, tree);

        while(toVisit.size() > 0) {
            if (!adjacentMatrix.hasAncestors(rootIndex)) {
                break;
            }

            String ancestor = null;
            for(Vertex a: adjacentMatrix.getAncestors(rootIndex)) {
                if (toVisit.contains(a.getLabel())) {
                    ancestor = a.getLabel();
                    break;
                }
            }

            if (ancestor == null) {
                throw new Exception("Todos os ancestrais da raiz já foram visitados. Dígrafo não conexo.");
            }

            toVisit.remove(ancestor);
            tree.addVertex(ancestor);
            tree.connectVertices(ancestor, root, null);
            root = ancestor;
            rootIndex = labelsIndexes.get(root);
            this.visit(root, toVisit, tree);
        }

        return tree;
    }

    private void visit(String current, LinkedHashSet<String> toVisit, Digraph tree) throws Exception{
        for (Vertex neighbor : super.getAdjacents(current)) {
            String label = neighbor.getLabel();
            if (!toVisit.contains(label)) {
                continue;
            }

            tree.addVertex(label);
            tree.connectVertices(current, label, null);
            toVisit.remove(label);
            visit(label, toVisit, tree);
        }
    }
}
