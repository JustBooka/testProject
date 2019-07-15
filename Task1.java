import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер месяца: ");
        int num = in.nextInt();
        System.out.print(getSeason(num));

    }

    private static String getSeason(int num) {
        if (num == 12 || num == 1 || num == 2)
            return "Зима";
        if (num == 3 || num == 4 || num == 5)
            return "Весна";
        if (num == 6 || num == 7 || num == 8)
            return "Лето";
        if (num == 9 || num == 10 || num == 11)
            return "Осень";
        else
            return "Укажите правильный месяц";
    }
}