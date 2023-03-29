package ui;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public void startButton() throws IOException {
        System.out.println("\nЕсли Вы \n покупатель - нажмите 1 \n сотрудник - нажмите 2 \n");

        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();

        while (!(choice instanceof Integer) || (choice > 2) || (choice < 1)) {
            System.out.println("Ввод некорректен, попробуйте еще раз");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                new ViewBuyer().input();
                new ViewBuyer().output();
                break;
            case 2:
                new ViewEmployee().input();
                new ViewEmployee().output();
                break;
        }
        sc.close();
    }
}
