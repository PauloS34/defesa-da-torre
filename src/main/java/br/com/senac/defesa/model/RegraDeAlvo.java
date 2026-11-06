package br.com.senac.defesa.model;

import java.util.List;

public abstract class RegraDeAlvo {
    public abstract Inimigo escolher(List<Inimigo> candidatos, Ponto torrePosicao);
    public abstract String getNome();
}