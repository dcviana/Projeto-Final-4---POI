package com.poi.main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Luiz {

    public static void main(String[] args) {
        processarSistemaOperacional("src/data1.csv");
    }

    public static void processarSistemaOperacional(String caminhoArquivo) {
        Map<String, Integer> frequencias = new HashMap<>();
        frequencias.put("Windows", 0);
        frequencias.put("Mac", 0);
        frequencias.put("Linux", 0);

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split("\t");
                String sistemaOperacional = dados[33].toLowerCase();

                if (sistemaOperacional.contains("windows")) {
                    frequencias.put("Windows", frequencias.get("Windows") + 1);
                }
                if (sistemaOperacional.contains("mac")) {
                    frequencias.put("Mac", frequencias.get("Mac") + 1);
                }
                if (sistemaOperacional.contains("linux")) {
                    frequencias.put("Linux", frequencias.get("Linux") + 1);
                }
            }

            System.out.println("Sistemas operacionais:");
            for (Map.Entry<String, Integer> entrada : frequencias.entrySet()) {
                System.out.println(entrada.getKey() + " : " + entrada.getValue());
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
