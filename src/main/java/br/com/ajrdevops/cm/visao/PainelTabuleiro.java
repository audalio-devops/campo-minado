package br.com.ajrdevops.cm.visao;

import br.com.ajrdevops.cm.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    public PainelTabuleiro(Tabuleiro tabuleiro) {
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCadaCampo((c -> add(new BotaoCampo(c))));

        tabuleiro.registrarObservador( e -> {

            //Garantir que exibirá mensagem após a atualização da tela
            SwingUtilities.invokeLater( () -> {
                if (e.isGanhou()) {
                    JOptionPane.showMessageDialog(this, "GANHOU!!! :)");
                } else {
                    JOptionPane.showMessageDialog(this, "Perdeu! :(");
                }

                tabuleiro.reiniciar();
            });

        });
    }
}
