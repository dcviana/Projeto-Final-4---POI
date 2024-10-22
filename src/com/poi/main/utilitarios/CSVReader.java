package com.poi.main.utilitarios;

import com.poi.main.implementacoes.LinguagemService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void lerCSV(String arquivo, LinguagemService service, int maxLinhas) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            br.readLine(); // Ignora o cabeçalho
            int contador = 0;

            while ((linha = br.readLine()) != null && contador < maxLinhas) {
                String[] colunas = linha.split(",");

                // A coluna LanguageHaveWorkedWith está na posição 0
                String linguagens = colunas[0]; // Ajuste o índice conforme necessário
                System.out.println("Linha lida: " + linha); // Debug
                if (!linguagens.equals("NA")) {
                    for (String linguagem : linguagens.split(";")) {
                        service.adicionarLinguagem(linguagem.trim());
                    }
                }
                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}