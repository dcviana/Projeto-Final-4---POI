package com.poi.main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Fernando {

    public static void main(String[] args) {

        processarFerramentasUsadas("src/data3.csv", 23);
    }

    public static void processarFerramentasUsadas(String caminhoArquivo, int indiceColunaFerramentas) {
        Map<String, Integer> frequencias = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }


                String[] dados = linha.split("\t");


                if (dados.length > indiceColunaFerramentas) {
                    String ferramentasUsadas = dados[indiceColunaFerramentas].toLowerCase();


                    String[] listaFerramentas = ferramentasUsadas.split(",");


                    for (String ferramenta : listaFerramentas) {
                        ferramenta = ferramenta.trim();
                        frequencias.put(ferramenta, frequencias.getOrDefault(ferramenta, 0) + 1);
                    }
                }
            }


            List<Entry<String, Integer>> topFerramentas = frequencias.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(5)
                    .collect(Collectors.toList());

            System.out.println("Quais são as ferramentas ou ambientes de desenvolvimento mais utilizados?:");
            for (Entry<String, Integer> entrada : topFerramentas) {
                System.out.println(entrada.getKey() + " : " + entrada.getValue());
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
