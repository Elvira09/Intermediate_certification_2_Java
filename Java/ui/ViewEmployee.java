package ui;

import java.io.IOException;
import java.util.Scanner;

import service.AppService;

public class ViewEmployee implements IView {

    @Override
    public void input() {
        System.out.println(
                "1 - посмотреть статистикку выпадения игрушек \n2 - посмотреть выданные призы \n3 - посмотреть остатки на складе \n4 - добавить игрушки на склад \n");

    }

    @Override
    public void output() throws IOException {
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();

        while (!(choice instanceof Integer) || (choice > 4) || (choice < 1)) {
            System.out.println("Ввод некорректен, попробуйте еще раз");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                new AppService().showData("D");
                break;
            case 2:
                new AppService().showData("R");

                break;
            case 3:
                new AppService().showData("S");

                break;
            case 4:
                new AppService().addDataWriter("S");
                break;
        }
        sc.close();
    }

}
