package br.com.felipesoftware.graph.core;

import java.util.ArrayList;
import java.util.List;

public class AdjacentMatrix {
    private int[][] matriz;
    private List<Vertex> vertices;
    private int qtdVertices;

    public AdjacentMatrix(List<Vertex> vertices) {
        this.vertices = vertices;
        this.qtdVertices = vertices.size();
        matriz = new int[qtdVertices][qtdVertices];
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

    public void addEdge(int initialVertexIndex, int finalVertexIndex) {
        Vertex initialVertex = vertices.get(initialVertexIndex);
        Vertex finalVertex = vertices.get(finalVertexIndex);
        if (initialVertexIndex == finalVertexIndex) {
            matriz[initialVertexIndex][initialVertexIndex] = 1;
            initialVertex.addGrau();
        } else {
            matriz[initialVertexIndex][finalVertexIndex] = 1;
            initialVertex.addGrau();
            matriz[finalVertexIndex][initialVertexIndex] = 1;
            finalVertex.addGrau();
        }
    }



    public List<Vertex> getAdjacents(int indexVertex) {
        int row = indexVertex;
        List<Vertex> adjacents = new ArrayList<>();
        for(int j = 0; j < vertices.size(); j++) {
            if (matriz[row][j] == 1) {
                Vertex vertex = vertices.get(j);
                adjacents.add(vertex);
            }
        }
        return adjacents;
    }
}
