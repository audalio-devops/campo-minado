package br.com.ajrdevops.cm.modelo;

import br.com.ajrdevops.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Campo {

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;


    private final int linha;
    private final int coluna;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {

        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if ((deltaGeral == 1 & !diagonal) || (deltaGeral == 2 & diagonal))
        {
            vizinhos.add(vizinho);
            return  true;
        }

        return false;
    }

    void alternarMarcacao() {
        if (!aberto){
            marcado = !marcado;
        }
    }

    boolean abrir() {

        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(Campo::abrir);
            }
            return  true;
        } else {
            return false;
        }

    }

    boolean vizinhancaSegura() {

        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado() {
        return marcado;
    }

    void minar() {
        minado = true;
    }

    public boolean isAberto() {
        return aberto;
    }
    public boolean isFechado() {
        return !isAberto();
    }
    public boolean isMinado() {
        return minado;
    }
    void setAberto(boolean aberto) {this.aberto = aberto;}
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;

        return desvendado || protegido;
    }

    long minasNaVizinhanca () {
        return vizinhos.stream().filter(v ->v.minado).count();
    }


    void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString() {
        if (marcado) {
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinhanca() >0) {
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return  "?";
        }
    }
}
