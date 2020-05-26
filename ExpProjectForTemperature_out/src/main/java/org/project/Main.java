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
        System.out.println("Добро пожаловать в сканер погоды!");
        while(true) {
            System.out.println("Нажмите 1 для того, чтобы вывести на экран данные, сохраненные в базе");
            System.out.println("Нажмите 2 для того, чтобы найти погоду в городе");
            System.out.println("Нажмите 9, чтобы выйти");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    List<Model> savedModels = logic.getSavedModels();
                    if (savedModels!=null){
                        logic.printModels(savedModels);
                    } else {
                        System.out.println("Сохраненных нет");
                    }
                    break;
                case 2:
                    System.out.println("Введите нзвание города: ");
                    String city = in.next();
                    logic.updateAndWriteTemperature(city, LocalDateTime.now());
                    break;
                case 9:
                    System.out.println("Завершение работы программы");
                    System.exit(0);
                default:
                    System.out.println("Некорректный пункт");
            }
        }



    }
}
