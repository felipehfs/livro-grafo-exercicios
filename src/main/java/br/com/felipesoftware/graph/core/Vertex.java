package br.com.felipesoftware.graph.core;

public class Vertex {
    private String label;
    private int grau;

    public Vertex(String label) throws Exception{
        boolean isLabelNullOrBlank = label == null || label != null && "".equals(label.trim());
        if (isLabelNullOrBlank) {
            throw new Exception("Não é permitido a inclusão de vétices com rótulo em branco.");
        }
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getGrau() {
        return grau;
    }

    public void addGrau() {
        grau++;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }
}
