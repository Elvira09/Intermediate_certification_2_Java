package ui;

import java.util.Scanner;

public class View implements IView{

    @Override
    public void input() {
        System.out.println("1 - Посмотреть список контрагентов");
        System.out.println("2 - Добавить контрагента");
        System.out.println("3 - Удалить контрагента");
        System.out.println("4 - Найти контрагента по названию");
    }

    @Override
    public void output() {
        Scanner sc = new Scanner(System.in);
        Integer choice1 = sc.nextInt();

        while (!(choice1 instanceof Integer) || (choice1 > 4) || (choice1 < 1)) {
            System.out.println("Ввод некорректен, попробуйте еще раз");
            choice1 = sc.nextInt();
        }

        switch (choice1) {
            case 1:
                // показать список
                System.out.println("написать метод - показать список");

                break;
            case 2:
                // добавить контрагегнта
                System.out.println("написать метод -добавить контрагегнта");


                System.out.println("Выберите способы связаться с контрагентом, после окончания вариантов выбора нажмите 0: ");

                System.out.println("21 - Телефон");
                System.out.println("22 - Электронная почта");
                System.out.println("23 - Адрес");
                System.out.println("24 - Аккуант VK");
                System.out.println("23 - Аккуант Telegram");

                break;
            case 3:
                // удалить контрагента
                System.out.println("написать метод -удалить контрагента");

                break;
            case 4:
                // найти контрагента
                System.out.println("написать метод -найти контрагента");

                
                System.out.println("41 - Посмотреть информацию о контрагенте");
                System.out.println("42 - Добавить новый способ связаться с контрагентом");
                System.out.println("43 - Удалить способ связаться с контрагентом");

                Integer choice2 = sc.nextInt();

                while (!(choice2 instanceof Integer) || (choice2 > 43) || (choice2 < 41)) {
                    System.out.println("Ввод некорректен, попробуйте еще раз");
                    choice2 = sc.nextInt();
                }
                switch (choice2) {
                    case 5:
                        // показать информацию о контрагенте
                        System.out.println("написать метод -показать информацию о контрагенте");

                        break;
                    case 6:
                        // добавить новый способ связаться с контрагентом"
                        System.out.println("написать метод -добавить новый способ связаться с контрагентом");

                        break;
                    case 7:
                        // удалить способ связаться с контрагентом
                        System.out.println("написать метод -удалить способ связаться с контрагентом");

                        break;
                }

                break;

        }
        sc.close();
    }

    
}
