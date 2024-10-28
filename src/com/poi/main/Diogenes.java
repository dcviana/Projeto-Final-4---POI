package com.poi.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Diogenes {
    public static void main(String[] args) {
        String csvFile = "src/data1.csv";
        analyzeRemoteWork(csvFile);
    }

    public static void analyzeRemoteWork(String csvFile) {
        Map<String, int[]> countryCounts = new HashMap<>();
        int totalRemote = 0;
        int totalInPerson = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                String country = values[15];
                String remoteWork = values[3];

                if ("NA".equals(country) || "NA".equals(remoteWork)) {
                    continue;
                }

                int[] counts = countryCounts.getOrDefault(country, new int[2]);
                if ("Fully remote".equals(remoteWork)) {
                    counts[0]++;
                    totalRemote++;
                } else {
                    counts[1]++;
                    totalInPerson++;
                }
                countryCounts.put(country, counts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, int[]>> sortedCountries = countryCounts.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue()[0] + entry2.getValue()[1], entry1.getValue()[0] + entry1.getValue()[1]))
                .collect(Collectors.toList());

        System.out.printf("\nQual o percentual de desenvolvedores que trabalha de forma remota ou presencial por país?\n\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-45s %-10s     \t %-10s%n", "País", "Remoto", "Presencial");
        System.out.println("--------------------------------------------------------------------------------");
        for (Map.Entry<String, int[]> entry : sortedCountries) {
            String country = entry.getKey();
            int remoteCount = entry.getValue()[0];
            int inPersonCount = entry.getValue()[1];
            int totalCount = remoteCount + inPersonCount;

            double remotePercentage = totalCount == 0 ? 0 : (double) remoteCount / totalCount * 100;
            double inPersonPercentage = totalCount == 0 ? 0 : (double) inPersonCount / totalCount * 100;

            String countryDisplay = country.length() > 45 ? country.substring(0, 42) + "..." : country;
            System.out.printf("%-45s %-5d (%-3.2f%%) \t %-5d (%-3.2f%%)%n",
                    countryDisplay, remoteCount, remotePercentage, inPersonCount, inPersonPercentage);
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-45s %-5d (%-3.2f%%) \t %-5d (%-3.2f%%)%n",
                "Total", totalRemote, (double) totalRemote / (totalRemote + totalInPerson) * 100,
                totalInPerson, (double) totalInPerson / (totalRemote + totalInPerson) * 100);
        System.out.println("--------------------------------------------------------------------------------");
    }
}