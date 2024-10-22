package com.poi.main;
/*
import com.poi.main.implementacoes.LinguagemService;
import com.poi.main.utilitarios.CSVReader;

public class Main {
    public static void main(String[] args) {
        LinguagemService service = new LinguagemService();
        int maxLinhas = 5; // Defina o número máximo de linhas que você deseja ler
        CSVReader.lerCSV("src/data5L.csv", service, maxLinhas);
        service.mostrarFrequencias();
    }
}*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        String csvFile = "src/teste.csv"; // Substitua pelo caminho correto
        String linha;
        Map<String, Integer> frequencias = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int linhaAtual = 0;

            while ((linha = br.readLine()) != null) {
                linhaAtual++;

                // Ignora a primeira linha (cabeçalho)
                if (linhaAtual == 1) {
                    continue;
                }

                // Verifica se a linha não está vazia
                if (linha.trim().isEmpty()) {
                    continue; // Pula linhas vazias
                }

                // Divide a linha em colunas
                String[] colunas = linha.split("\t");

                // Verifica se a linha possui colunas suficientes
                if (colunas.length > 18) {
                    // A coluna "LanguageHaveWorkedWith" está no índice 18
                    String linguagens = colunas[20].trim(); // Acesso direto à coluna

                    // Verifica se a coluna não está vazia ou não contém "NA"
                    if (!linguagens.isEmpty() && !linguagens.equals("NA")) {
                        // Divide as linguagens por ponto e vírgula
                        String[] linguagensArray = linguagens.split(";");
                        for (String linguagem : linguagensArray) {
                            linguagem = linguagem.trim(); // Remove espaços
                            if (!linguagem.isEmpty()) { // Ignora strings vazias
                                frequencias.put(linguagem, frequencias.getOrDefault(linguagem, 0) + 1);
                            }
                        }
                    }
                } else {
                    System.out.println("Linha " + linhaAtual + " não possui colunas suficientes.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Exibir as frequências
        for (Map.Entry<String, Integer> entrada : frequencias.entrySet()) {
            System.out.println("Linguagem: " + entrada.getKey() + ", Frequência: " + entrada.getValue());
        }
    }
}