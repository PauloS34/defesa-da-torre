package br.com.senac.defesa.model;

import java.util.List;

public class TorreDeGelo extends Torre {
    public TorreDeGelo(int id, Ponto posicao) {
        super(id, "GELO", posicao, 3.5);
    }

    @Override
    protected void disparar(Inimigo alvo, List<Inimigo> inimigos) {
        alvo.receberDano(3);
        alvo.desacelerar(12);
    }

    @Override
    protected int tempoDeRecarga() {
        return 2;
    }
}