package br.com.senac.defesa.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Torre {

    protected final int id;
    protected final String tipo;
    protected final Ponto posicao;
    protected final double alcance;

    protected RegraDeAlvo regraDeAlvo = new MaisProximo();
    protected int recarga;
    protected double angulo;
    protected Ponto tiroAtual;

    public Torre(int id, String tipo, Ponto posicao, double alcance) {
        this.id = id;
        this.tipo = tipo;
        this.posicao = posicao;
        this.alcance = alcance;
    }

    public final void agir(List<Inimigo> inimigos) {
        tiroAtual = null;

        if (recarga > 0) {
            recarga--;
            return;
        }

        Inimigo alvo = escolherAlvo(inimigos);
        if (alvo == null) {
            return;
        }

        angulo = Math.atan2(alvo.getPosicao().z() - posicao.z(), alvo.getPosicao().x() - posicao.x());

        disparar(alvo, inimigos);

        tiroAtual = alvo.getPosicao();
        recarga = tempoDeRecarga();
    }

    protected abstract void disparar(Inimigo alvo, List<Inimigo> inimigos);

    protected abstract int tempoDeRecarga();

    private Inimigo escolherAlvo(List<Inimigo> inimigos) {
        List<Inimigo> candidatosNoAlcance = new ArrayList<>();
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo() && posicao.distanciaAte(inimigo.getPosicao()) <= alcance) {
                candidatosNoAlcance.add(inimigo);
            }
        }

        if (candidatosNoAlcance.isEmpty()) {
            return null;
        }

        return regraDeAlvo.escolher(candidatosNoAlcance, posicao);
    }

    public void setRegraDeAlvo(RegraDeAlvo regraDeAlvo) {
        this.regraDeAlvo = regraDeAlvo;
    }

    public void setPrioridade(String prioridade) {
        this.regraDeAlvo = switch (prioridade) {
            case "FORTE" -> new MaisForte();
            case "AVANCADO" -> new MaisAvancado();
            default -> new MaisProximo();
        };
    }

    public String getPrioridade() {
        return regraDeAlvo.getNome();
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public Ponto getPosicao() {
        return posicao;
    }

    public double getAlcance() {
        return alcance;
    }

    public double getAngulo() {
        return angulo;
    }

    public Ponto getTiroAtual() {
        return tiroAtual;
    }
}