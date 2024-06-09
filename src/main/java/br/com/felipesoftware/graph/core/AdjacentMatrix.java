package br.com.felipesoftware.graph.core;

import java.util.*;

public class AdjacentMatrix {
    private int[][] matriz;
    private List<Vertex> vertices;
    private int qtdVertices;
    private Map<Integer, List<Vertex>> ancestors;

    public AdjacentMatrix(List<Vertex> vertices) {
        this.vertices = vertices;
        this.qtdVertices = vertices.size();
        matriz = new int[qtdVertices][qtdVertices];
        this.ancestors = new HashMap<>();
        initializeMatrix();
    }

    private void initializeMatrix() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addDirectionalEdge(int initialVertexIndex, int finalVertexIndex, Integer weight) {
        weight = weight == null ? 1: weight;
        Vertex initialVertex = vertices.get(initialVertexIndex);
        if (initialVertexIndex == finalVertexIndex) {
            matriz[initialVertexIndex][initialVertexIndex] = weight;
            initialVertex.addGrau();
        } else {
            matriz[initialVertexIndex][finalVertexIndex] = weight;
            Vertex finalVertex = vertices.get(finalVertexIndex);
            finalVertex.addGrau();
        }
        this.addAncestor(finalVertexIndex, initialVertex);
    }

    public int getWeight(int initialVertexIndex, int finalVertexIndex) {
        return this.matriz[initialVertexIndex][finalVertexIndex];
    }

    public int getQtdVertices() {
        return qtdVertices;
    }

    public void copyValuesTo(AdjacentMatrix destinationMatrix) throws Exception {
        if (destinationMatrix.getQtdVertices() < this.qtdVertices) {
            throw new Exception("Somente é possível executar cópias em matrizes com dimensões iguais " +
                    "ou a matriz de destino deve ter dimensões maiores que a matriz de origem");
        }
        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                destinationMatrix.writeInCell(i, j, matriz[i][j]);
            }
        }
    }

    private void writeInCell(int row, int col, int value) {
        this.matriz[row][col] = value;
    }

    private void addAncestor(int vertexIndex, Vertex ancestor) {
        if (this.ancestors.get(vertexIndex) == null) {
            List<Vertex> ancestors = new ArrayList<>();
            ancestors.add(ancestor);
            this.ancestors.put(vertexIndex, ancestors);
        } else {
            this.ancestors.get(vertexIndex).add(ancestor);
        }
    }

    public List<Vertex> getAncestors(int vertexIndex) {
        if (this.ancestors.get(vertexIndex) == null) {
            return Collections.emptyList();
        }
        return this.ancestors.get(vertexIndex);
    }

    public boolean hasAncestors(int vertexIndex) {
        return this.ancestors.containsKey(vertexIndex);
    }

    public void addEdge(int initialVertexIndex, int finalVertexIndex, Integer weight) {
        weight = weight == null? 1: weight;
        Vertex initialVertex = vertices.get(initialVertexIndex);
        Vertex finalVertex = vertices.get(finalVertexIndex);
        if (initialVertexIndex == finalVertexIndex) {
            matriz[initialVertexIndex][initialVertexIndex] = weight;
            initialVertex.addGrau();
        } else {
            matriz[initialVertexIndex][finalVertexIndex] = weight;
            initialVertex.addGrau();
            matriz[finalVertexIndex][initialVertexIndex] = weight;
            finalVertex.addGrau();
        }
    }


    public List<Vertex> getAdjacents(int indexVertex) {
        int row = indexVertex;
        List<Vertex> adjacents = new ArrayList<>();
        for(int j = 0; j < qtdVertices; j++) {
            if (matriz[row][j] != 0) {
                Vertex vertex = vertices.get(j);
                adjacents.add(vertex);
            }
        }
        return adjacents;
    }
}
