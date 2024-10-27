package com.poi.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mario {
    public static void main(String[] args) {
        String caminhoArquivo = "data1.csv";

        try {
            Map<String, Long> contagemLinguagens = Files.lines(Paths.get(caminhoArquivo))
                    .skip(1)
                    .map(linha -> linha.split("\t"))
                    .filter(colunas -> colunas.length > 0)
                    .filter(colunas -> colunas.length > 19)
                    .map(colunas -> colunas[19])
                    .flatMap(languages -> Arrays.stream(languages.split(";")))
                    .map(String::trim)
                    .filter(language -> !language.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            System.out.println("1 > Qual a distribuição de desenvolvedores por linguagem de programação?\n");
            System.out.println("Foi verificada a quantidade de vezes que cada linguagem de programação foi citada na pesquisa." +
                            "\nVale ressaltar que alguns desenvolvedores não citaram linguagens, bem como, alguns citaram mais de uma.\n");


            contagemLinguagens.forEach((linguagem, contagem) -> System.out.println(linguagem + ": " + contagem));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
