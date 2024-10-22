package com.poi.main;

import com.poi.main.implementacoes.LinguagemService;
import com.poi.main.utilitarios.CSVReader;

public class Main {
    public static void main(String[] args) {
        LinguagemService service = new LinguagemService();
        int maxLinhas = 5; // Defina o número máximo de linhas que você deseja ler
        CSVReader.lerCSV("src/data5L.csv", service, maxLinhas);
        service.mostrarFrequencias();
    }
}