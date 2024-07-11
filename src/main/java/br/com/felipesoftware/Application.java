package br.com.felipesoftware;

import java.util.Scanner;

public class Application {

    private Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Application app = new Application();
        app.menu();

        while (true) {
            System.out.print("Digite a opção desejada");
            String option  = app.read().toUpperCase();
        }
    }

    private String read() {
        return input.next();
    }

    private void menu() {
        StringBuilder text = new StringBuilder();
        text.append("*** Bem vindo ***\n")
                .append("* Escolha uma das opções abaixo:  *\n")
                .append("* |G|rafo      *\n")
                .append("* |D|igrafo    *\n")
                .append("* |V|értica    *\n")
                .append("* |A|resta     *\n")
                .append("* |A|resta com |Pe|so  *\n")
                .append("* Busca por |P|rofundidade  *\n")
                .append("* Busca por |L|argura  *\n")
                .append("* |AG| - Àrvore Geradora  *\n")
                .append("* Árvore Geradora Mínima por |Pr|im  *\n")
                .append("* Algoritmo de |Dij|ksta   *\n")
                .append("* Algoritmo de |Fl|oyd Warshall   *\n")
                .append("* Gerar representação Graph|Viz|   *\n")
                .append("* |S|air   *\n");

        System.out.println(text.toString());
    }
}