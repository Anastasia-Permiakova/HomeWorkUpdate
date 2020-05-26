package kalendar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Kalendar {
    private int day;
    private int month;
    private int year;
    private static int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Kalendar(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int dayOfWeek() {
        int day = this.day;
        int month = this.month;
        int year = this.year;
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        // day = q
        int h;
        int K = year % 100;
        int J = year / 100;
        int dayOfWeek;
        h = (day + (13 * (month + 1)) / 5 + K + (K / 4) + (J / 4) + 5 * J) % 7;

        dayOfWeek = ((h + 5) % 7) + 1;
        return dayOfWeek;
    }

    public void nextMonth() {
        this.day = 1;
        if (this.month != 12) {
            this.month += 1;
        } else {
            this.month = 1;
            this.year += 1;
        }

    }

    public void prevMonth() {
        this.day = 1;
        if (this.month != 1) {
            this.month -= 1;
        } else {
            this.month = 12;
            this.year -= 1;
        }

    }

    public String tableOfMonth() {
        StringBuilder calendar = new StringBuilder();
        calendar.append("\t\t" + month + " " + year + "%n");
        calendar.append("Po Ut St Ct Pa So Ne %n");
        this.day = 1;
        int firstDay = dayOfWeek();
        for (int i = 0; i < firstDay - 1; i++) {
            calendar.append("   ");
        }
        for (int i = 0; i < setDaysInMonth(year)[month - 1]; i++) {
            calendar.append(String.format("%2d ", this.day));
            if (dayOfWeek() == 7) {
                calendar.append("%n");
            }
            this.day++;
        }
        calendar.append("%n");
        return calendar.toString();
    }


    public static boolean leapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    public static int daysInYear(int year) {
        int days = leapYear(year) ? 366 : 365;
        return days;
    }

    public static int[] setDaysInMonth(int year) {
        if (leapYear(year)) {
            daysInMonth[1] = 29;
        }
        return daysInMonth;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Kalendar date = new Kalendar(15, 4, 2020);

        System.out.printf(date.tableOfMonth());

        int choice;
        while (true) {
            System.out.println("Minuly mesic 1");
            System.out.println("Pristi mesic 2");
            System.out.println("Vychod 3");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    date.prevMonth();
                    System.out.printf(date.tableOfMonth());
                    break;
                case 2:
                    date.nextMonth();
                    System.out.printf(date.tableOfMonth());
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

    }

}
