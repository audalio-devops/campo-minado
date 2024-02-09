package br.com.ajrdevops.cm;

import br.com.ajrdevops.cm.modelo.Tabuleiro;
import br.com.ajrdevops.cm.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

        new TabuleiroConsole(tabuleiro);

        //System.out.println(tabuleiro);
    }
}
