package br.com.felipesoftware.graph.core;

import java.util.*;

public class Graph {
    private List<Vertex> vertices = new ArrayList<>();
    private int maxQuantityVertices;
    private int currentVertices;
    private boolean isMaxQuantityDefined;
    private Map<String, Integer> labelsIndexes = new HashMap<>();
    private AdjacentMatrix adjacentMatrix;

    public Graph() {
        maxQuantityVertices = 10;
    }

    public Graph(int verticesQuantity) {
        if (verticesQuantity <= 0) {
            throw new IllegalArgumentException("A quantidade máxima de vértices deve ser maior ou igual à 1");
        }
        maxQuantityVertices = verticesQuantity;
        isMaxQuantityDefined = true;
    }

    public void addVertex(String label) throws Exception {
        if (currentVertices <= maxQuantityVertices - 1){
            Vertex newVertex = new Vertex(label);
            this.vertices.add(newVertex);
            this.labelsIndexes.put(label, currentVertices);
            currentVertices++;
        }else {
            throw new Exception("A quantidade de vértices permitida (" + currentVertices + ") foi excedida.");
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(String label) {
        this.existsVertexOrThrow(label);
        int index = this.labelsIndexes.get(label);
        return this.vertices.get(index);
    }

    public void connectVertices(String initialVertexLabel, String finalVertexLabel) throws Exception {
        if (!this.existsVertice(initialVertexLabel) || !this.existsVertice(finalVertexLabel)) {
            throw new Exception("Para adicionar uma aresta ambos os vértices devem existir");
        }
        createAdjacentMatrix();
        int finalVertexIndex = this.labelsIndexes.get(initialVertexLabel);
        int initialVertexIndex = this.labelsIndexes.get(finalVertexLabel);
        this.adjacentMatrix.addEdge(initialVertexIndex ,finalVertexIndex);
    }

    public List<Vertex> getAdjacents(String vertex) {
        this.existsVertexOrThrow(vertex);
        int indexVertex = this.labelsIndexes.get(vertex);
        return this.adjacentMatrix.getAdjacents(indexVertex);
    }

    public Graph spaningTreeByDeepSearch() throws Exception{
        Graph tree = new Graph();
        Stack<Vertex> stack = new Stack<>();
        LinkedHashSet<String> visitedVertices = new LinkedHashSet<>();
        List<Vertex> vertices = getVertices();

        for (Vertex v : vertices) {
            tree.addVertex(v.getLabel());
        }

        Vertex vertexStartingPoint = vertices.get(0);
        visitedVertices.add(vertexStartingPoint.getLabel());
        stack.push(vertexStartingPoint);

        while (!stack.isEmpty()) {
            Vertex vertexAnalysed = stack.peek();
            Vertex nextVertex = getNextVertex(vertexAnalysed, visitedVertices);

            if (nextVertex == null) {
                stack.pop();
            } else {
                String label = nextVertex.getLabel();
                visitedVertices.add(label);
                stack.push(nextVertex);
                tree.connectVertices(vertexAnalysed.getLabel(), nextVertex.getLabel());
            }
        }

        return tree;
    }

    private Vertex getNextVertex(Vertex vertex, LinkedHashSet<String> visitedVertices) {
        List<Vertex> adjancents = getAdjacents(vertex.getLabel());
        for (int i = 0; i < adjancents.size(); i++) {
            Vertex adjacent = adjancents.get(i);
            boolean nonVisitedYet = !visitedVertices.contains(adjacent.getLabel());
            if (nonVisitedYet) {
                return adjacent;
            }
        }
        return null;
    }

    private void createAdjacentMatrix() {
        if (this.adjacentMatrix == null) {
            this.adjacentMatrix = new AdjacentMatrix(new ArrayList<>(this.vertices));
        }
    }

    private boolean existsVertice(String label) {
        int index = this.labelsIndexes.get(label);
        return this.vertices.get(index) != null;
    }

    private boolean existsVertexOrThrow(String label) {
        if (!existsVertice(label)) {
            throw new IllegalArgumentException("O vértice não existe");
        }
        return  true;
    }
}
