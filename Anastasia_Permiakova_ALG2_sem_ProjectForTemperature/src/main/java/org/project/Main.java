package org.project;

import org.project.app.Logic;
import org.project.app.MainLogic;
import org.project.dto.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Logic logic = MainLogic.getInstance();
        System.out.println("Vitame vas v PocasiScanner!");
        while(true) {
            System.out.println("1 - zobrazit data uložená v databázi");
            System.out.println("2 - vyhledat počasí");
            System.out.println("9 - exit");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    List<Model> savedModels = logic.getSavedModels();
                    if (savedModels!=null){
                        logic.printModels(savedModels);
                    } else {
                        System.out.println("");
                    }
                    break;
                case 2:
                    System.out.println("Ukaž město: ");
                    String city = in.next();
                    logic.updateAndWriteTemperature(city, LocalDateTime.now());
                    break;
                case 9:
                    System.out.println("Vypnutí programu");
                    System.exit(0);
                default:
                    System.out.println("Neplatný vstup");
            }
        }



    }
}
