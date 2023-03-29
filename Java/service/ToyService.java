package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Toy;
import util.FileService;

public class ToyService {
    Scanner sc = new Scanner(System.in);

    // добавление игрушки на склад
    public Toy addNewToy() {
        Integer id = Toy.newId("S");

        System.out.println("Введите название игрушки: ");
        String toy = sc.nextLine();

        System.out.println("Введите количество: ");
        Integer quantityInStock = sc.nextInt();
        
        Double dropFrequency = 0.0;

        Toy newToy = new Toy(id, toy, quantityInStock, dropFrequency);

        return newToy;
    }

    // розыгрыш игрушек
    public Toy rafflePrizes() {
        List<Toy> toys = new FileService().dataReader("s");

        // добавляем возможность проигрыша
        for (int i = 0; i < (toys.size() / 3); i++) {
            toys.add(null);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(toys.size());
        Toy prize = toys.get(randomIndex);
        if ((prize == null) || (prize.getQuantityInStock() == 0))
            System.out.println("Ой, ПУСТО - ничего не выпало ");
        else
            System.out.println("Вам выпал/ла:  " + prize.getToy());

        return prize;
    }

    // поиск игрушки
    public Toy search(String choiceFile, Integer id) {
        List<Toy> toys = new FileService().dataReader(choiceFile);
        List<Toy> result = new ArrayList<Toy>();
        for (Toy item : toys) {
            if (item.getId() != id)
                continue;
            else
                result.add(item);
        }
        return result.get(0);
    }

    // получение приза
    public Toy receivingPrize() {
        List<Toy> prizes = new FileService().dataReader("P");

        System.out.println("Выберите приз из выпавших игрушек: ");
        for (Toy item : prizes) {
            System.out.println(item.getId() + " " + item.getToy());
        }
        System.out.println("Укажите номер игрушки, которую хотите забрать: ");

        Integer id = sc.nextInt();
        Toy prize = search("P", id);
        
        System.out.println("Поздравляем Вас с выигрышем!!!");

        return prize;
    }

    // статистика выпадения
    public List<Toy> toyDropStats() {
        List<Toy> statistics = new FileService().dataReader("D");
        List<Toy> newStatistics = new ArrayList<Toy>();

        Double sumStats = 0.0;
        for (Toy item : statistics) {
            sumStats += item.getQuantityInStock();
        }
        for (Toy item : statistics) {
            item.setDropFrequency(100.0 / sumStats * item.getQuantityInStock());
            newStatistics.add(item);
        }

        Collections.sort(newStatistics);

        List<Toy> result = new ArrayList<Toy>();
        while (!statistics.isEmpty()) {
            Iterator<Toy> oldStatisticsIterator = statistics.iterator();
            Toy firstToy = oldStatisticsIterator.next();
            Integer quantityInStock = firstToy.getQuantityInStock();
            Double dropFrequency = firstToy.getDropFrequency();
            oldStatisticsIterator.remove();

            while (oldStatisticsIterator.hasNext()) {
                Toy oldToy = oldStatisticsIterator.next();
                if (firstToy.getId() == oldToy.getId()) {
                    oldStatisticsIterator.remove();
                    quantityInStock += oldToy.getQuantityInStock();
                    dropFrequency += oldToy.getDropFrequency();
                }
            }

            if (quantityInStock != firstToy.getQuantityInStock() && dropFrequency != firstToy.getDropFrequency()) {
                result.add(new Toy(firstToy.getId(), firstToy.getToy(), quantityInStock, dropFrequency));
            } else {
                result.add(firstToy);
            }
        }
        Collections.sort(result);
        return result;
    }

}
