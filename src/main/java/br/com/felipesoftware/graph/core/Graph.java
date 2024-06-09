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

    public void connectVertices(String initialVertexLabel, String finalVertexLabel, Integer weight) throws Exception {
        if (!this.existsVertice(initialVertexLabel) || !this.existsVertice(finalVertexLabel)) {
            throw new Exception("Para adicionar uma aresta ambos os vértices devem existir");
        }
        createAdjacentMatrix();
        int finalVertexIndex = this.labelsIndexes.get(initialVertexLabel);
        int initialVertexIndex = this.labelsIndexes.get(finalVertexLabel);
        this.adjacentMatrix.addEdge(initialVertexIndex ,finalVertexIndex, weight);
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
                tree.connectVertices(vertexAnalysed.getLabel(), nextVertex.getLabel(), null);
            }
        }

        return tree;
    }

    public int getWeight(String initialVertexLabel, String finalVerticeLabel) {
        int initialVertexIndex = labelsIndexes.get(initialVertexLabel);
        int finalVertexIndex = labelsIndexes.get(finalVerticeLabel);
        return adjacentMatrix.getWeight(initialVertexIndex, finalVertexIndex);
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

    void createAdjacentMatrix() throws Exception{
        if (this.adjacentMatrix == null) {
            this.adjacentMatrix = new AdjacentMatrix(new ArrayList<>(this.vertices));
        } else {
            int qtdVertexMatrix = this.adjacentMatrix.getQtdVertices();
            if (this.vertices.size() != qtdVertexMatrix) {
                AdjacentMatrix adjacentMatrixTemp = new AdjacentMatrix(this.vertices);
                this.adjacentMatrix.copyValuesTo(adjacentMatrixTemp);
                this.adjacentMatrix = adjacentMatrixTemp;
            }
        }
    }

    public Map<String, Integer> getLabelsIndexes() {
        return labelsIndexes;
    }

    public AdjacentMatrix getAdjacentMatrix() {
        return adjacentMatrix;
    }

    public boolean existsVertice(String label) {
        return this.labelsIndexes.get(label) != null;
    }

    public boolean existsVertexOrThrow(String label) {
        if (!existsVertice(label)) {
            throw new IllegalArgumentException("O vértice não existe");
        }
        return  true;
    }
}
