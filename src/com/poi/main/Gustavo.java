package com.poi.main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Gustavo {

    public static void main(String[] args) {
        processarAnosDeExperiencia("src/data1.csv");
    }

    public static void processarAnosDeExperiencia(String caminhoArquivo) {
        Map<String, Integer> distribuicaoExperiencia = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split("\t");
                String anosExperiencia = dados[54]; // Supondo que a coluna 'WorkExp' é a 55ª (índice 54)

                distribuicaoExperiencia.put(anosExperiencia, distribuicaoExperiencia.getOrDefault(anosExperiencia, 0) + 1);
            }

            System.out.println("Distribuição de desenvolvedores por anos de experiência:");
            for (Map.Entry<String, Integer> entrada : distribuicaoExperiencia.entrySet()) {
                System.out.println(entrada.getKey() + " anos: " + entrada.getValue() + " desenvolvedores");
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
