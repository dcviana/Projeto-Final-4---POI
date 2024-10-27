package com.poi.main;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Luiz {

    public void MostrarSistemasOperacionais(String filePath) {
        String targetColumn = "OpSysProfessional use";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] headers = br.readLine().split("\t");
            int targetIndex = IntStream.range(0, headers.length)
                    .filter(i -> headers[i].equals(targetColumn))
                    .findFirst()
                    .orElse(-1);

            if (targetIndex == -1) {
                System.out.println("Coluna não encontrada.");
                return;
            }
            Map<String, Long> osCount = br.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> line.split("\t"))
                    .filter(columns -> columns.length > targetIndex)
                    .map(columns -> columns[targetIndex].trim())
                    .filter(os -> !os.isEmpty() && !os.equalsIgnoreCase("NA"))
                    .collect(Collectors.groupingBy(os -> os, Collectors.counting()));

            Optional<Map.Entry<String, Long>> mostUsedOS = osCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue());
            mostUsedOS.ifPresent(entry -> System.out.println(
                    entry.getKey() + " - " + entry.getValue() + " pessoas"
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Luiz sistema = new Luiz();
        String filePath = "src/data1.csv";
        sistema.MostrarSistemasOperacionais(filePath);    }
}

