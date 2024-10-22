package com.poi.main.implementacoes;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class LinguagemService {
    private Map<String, Linguagem> linguagens = new HashMap<>();

    public void adicionarLinguagem(String nome) {
        if (linguagens.containsKey(nome)) {
            linguagens.get(nome).incrementarFrequencia();
        } else {
            linguagens.put(nome, new Linguagem(nome, 1));
        }
    }

    public void mostrarFrequencias() {
        for (Linguagem linguagem : linguagens.values()) {
            System.out.println("Linguagem: " + linguagem.getNome() + ", Frequência: " + linguagem.getFrequencia());
        }
    }
}
