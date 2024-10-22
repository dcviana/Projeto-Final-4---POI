package com.poi.main.implementacoes;

import com.poi.main.interfaces.ILinguagem;

public class Linguagem implements ILinguagem {
    private String nome;
    private int frequencia;

    public Linguagem(String nome, int frequencia) {
        this.nome = nome;
        this.frequencia = frequencia;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getFrequencia() {
        return frequencia;
    }

    public void incrementarFrequencia() {
        this.frequencia++;
    }
}