
package Shapes;

import java.util.ArrayList;
import java.util.Scanner;

public class ShapesApp {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Shape> shapes = new ArrayList();

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = readChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    clearObjects();
                    break;
                case 2:
                    addSquare();
                    break;
                case 3:
                    addRectangle();
                    break;
                case 4:
                    addCircle();
                    break;
                case 5:
                    printObjects();
                    break;
                case 6:
                    computeArea();
                    break;
                case 7:
                    findWithMaxArea();
                    break;
                case 8:
                    getObjectInfo();
                    break;
                case 9:
                    sortByArea();
                    break;
                default:
                    System.out.println("Chybna volba");
            }
        }
        while (choice != 0);
    }

    public static void displayMenu() {
        System.out.println("");
        System.out.println("1 nova sada");
        System.out.println("2 pridat ctverec");
        System.out.println("3 pridat obdelnik");
        System.out.println("4 pridat kruh");
        System.out.println("5 vypsat geometricke objekty");
        System.out.println("6 vypocitat celkovou plochu");
        System.out.println("7 vypsat objekt s nejvetsi plochou");
        System.out.println("8. Vypis plochu vybraneho utvaru");
        System.out.println("9.Třídění podle plochy");
        System.out.println("0 konec programu");

    }

    private static int readChoice() {
        return sc.nextInt();
    }

    private static void addSquare() {
        Double a;
        System.out.println("Zadejte delku strany ctverce");
        a = sc.nextDouble();
        shapes.add(new Square(a));

    }

    private static void addRectangle() {
        Double a, b;
        System.out.println("Zadejte delku strany a");
        a = sc.nextDouble();
        System.out.println("Zadejte delku strany b");
        b = sc.nextDouble();
        shapes.add(new Rectangle(a, b));
    }

    private static void addCircle() {
        Double r;
        System.out.println("Zadejte polomer kruhu");
        r = sc.nextDouble();
        shapes.add(Circle.getInstanceR(r));
    }

    private static void computeArea() {
        double allArea = 0;
        for (Shape shape : shapes) {
            allArea += shape.computeArea();
        }
        System.out.println("Celkova plocha je: " + allArea);
    }

    private static void clearObjects() {
        shapes.clear();
    }

    private static void printObjects() {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    private static void findWithMaxArea() {
        double max = -1;
        Shape maxShape = null;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).computeArea() > max) {
                max = shapes.get(i).computeArea();
                maxShape = shapes.get(i);
            }
        }
        System.out.println("Nejvetsi plochu ma " + maxShape + " " + maxShape.computeArea());

    }

    private static void getObjectInfo() {
        int choice;
        System.out.println("Vyber si objekt: ");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
        choice = sc.nextInt();
        Shape shape = shapes.get(choice);
        System.out.println(shape.toString());
        System.out.println(shape.computeArea());
    }

    private static void sortByArea() {
        int n = shapes.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (shapes.get(j).computeArea() > shapes.get(j + 1).computeArea()) {

                    shapes.add(j, shapes.get(j + 1));
                    shapes.remove(j+2);

                }
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }


}

