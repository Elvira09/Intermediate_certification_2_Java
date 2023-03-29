package ui;

import java.io.IOException;
import java.util.Scanner;

import service.AppService;

public class ViewBuyer implements IView {

    @Override
    public void input() {
        System.out.println("Хотите поучаствовать в розыгрыше призов ? \n 1 - Да \n 2 - Нет \n");
        
    }

    @Override
    public void output() throws IOException {        
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();

        while (!(choice instanceof Integer) || (choice > 2) || (choice < 1)) {
            System.out.println("Ввод некорректен, попробуйте еще раз");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                new AppService().prizeWriter();
                new AppService().prizesReceived();
                new AppService().toyDropStatsWrite();
                new AppService().changeDataWriter();
                break;
            case 2:
                System.out.println("Очень жаль, а мы хотели Вас порадовать. ");
                break;
        }
        sc.close();        
    }
    
}
