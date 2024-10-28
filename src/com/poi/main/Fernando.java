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
        // Passe o caminho do arquivo e o índice da coluna para ToolsTechHaveWorkedWith
        processarFerramentasUsadas("src/data3.csv", 23); // Atualize o índice de acordo com a posição de ToolsTechHaveWorkedWith
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

                // Ajuste aqui caso o delimitador de linha seja diferente
                String[] dados = linha.split("\t");

                // Verifica se a linha tem colunas suficientes
                if (dados.length > indiceColunaFerramentas) {
                    String ferramentasUsadas = dados[indiceColunaFerramentas].toLowerCase();

                    // Divide as ferramentas por vírgula ou outro delimitador, se necessário
                    String[] listaFerramentas = ferramentasUsadas.split(",");

                    // Incrementa a contagem para cada ferramenta na lista
                    for (String ferramenta : listaFerramentas) {
                        ferramenta = ferramenta.trim(); // Remove espaços extras
                        frequencias.put(ferramenta, frequencias.getOrDefault(ferramenta, 0) + 1);
                    }
                }
            }

            // Ordena as ferramentas por frequência em ordem decrescente e limita aos 5 principais
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
