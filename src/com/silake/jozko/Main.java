package com.silake.jozko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //for (int i = 0; i < args.length; i++) {
        File subor = new File(args[0]);
        String[] riadky = new String[2];
        Scanner scanner = null;
        try {
            scanner = new Scanner(subor);
            for(int i = 0; i < 2; i++) {
                if(scanner.hasNextLine()) {
                    riadky[i] = scanner.nextLine();
                } else {
                    System.out.println("Chýba riadok!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Súbor " + subor.getName() + " neexistuje");
        } finally {
            if(scanner!=null)
                scanner.close(); //nech sa stane èoko¾vek, korektne uzavrieme súbor
        }

        int pocet_stromov = Integer.parseInt(riadky[0]);
        String[] postup = riadky[1].split(" ", -1);

        int stromoradie[] = new int[pocet_stromov];

        for (int i = 0; i < pocet_stromov; i++) {
            stromoradie[i] = Integer.parseInt(postup[i]);
        }

        // Na tomto mieste mame stromoradie
        boolean aktualna_kladnost = false;
        boolean predch_kladnost = false;
        Skupina predch_skupina = null;
        int aktualny_pocet_stromov = 0;
        int aktualny_zaciatocny_strom = 0;
        Skupina prva_skupina = null;

        for (int i = 0; i < pocet_stromov ; i++) {
            if (stromoradie[i]>0) {
                aktualna_kladnost = true;
            }
            else {
                aktualna_kladnost = false;
            }
            if (i == 0) {
                predch_kladnost = aktualna_kladnost;
            }

            if (aktualna_kladnost == predch_kladnost) {
                aktualny_pocet_stromov++;
            } else {
                Skupina s = new Skupina(predch_kladnost, aktualny_zaciatocny_strom, aktualny_pocet_stromov, stromoradie);
                if (prva_skupina == null) {
                    prva_skupina = s;
                    predch_skupina = s;
                } else {
                    predch_skupina.setDalsia_skupina(s);
                    predch_skupina = s;
                }
                aktualny_pocet_stromov = 1;
                aktualny_zaciatocny_strom = i;
            }
            predch_kladnost = aktualna_kladnost;
        }
        Skupina s = new Skupina(predch_kladnost, aktualny_zaciatocny_strom, aktualny_pocet_stromov++, stromoradie);
        predch_skupina.setDalsia_skupina(s);

        for (Skupina i = prva_skupina; i != null ; i = i.getDalsia_skupina()) {
            while (i.oplati_sa_zlucit()) {
                i.zluc();
            }
        }

        int max = 0;
        Skupina maximum = null;

        for (Skupina i = prva_skupina; i != null ; i = i.getDalsia_skupina()) {
           if (i.getRozdiel_jablk() > max) {
               maximum = i;
               max = i.getRozdiel_jablk();
           }
        }
        System.out.print(maximum.getZaciatocny_strom() + 1 + " " + maximum.pocet_stromov);
    }
}
