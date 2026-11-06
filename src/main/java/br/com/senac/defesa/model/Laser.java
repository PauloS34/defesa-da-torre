package br.com.senac.defesa.model;

import java.util.List;

public class Laser extends Torre {
    public Laser(int id, Ponto posicao) {
        super(id, "LASER", posicao, 5.0);
    }

    @Override
    protected void disparar(Inimigo alvo, List<Inimigo> inimigos) {
        alvo.receberDano(2);
    }

    @Override
    protected int tempoDeRecarga() {
        return 0;
    }
}