package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import model.Toy;
import util.FileService;

public class AppService {
    
    // добавление элемента
    public void addDataWriter(String choiceFile) {
        new FileService().dataWriter(choiceFile, new ToyService().addNewToy(), "A");
    }

    // очистка файла перед перезаписью
    public void clearFile(String choiceFile) {
        String path = new FileService().selectFile(choiceFile);
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(path),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // запись выпавших призов в файл
    public void prizeWriter() throws IOException {
        System.out
                .println("У Вас есть три попытки для розыгрыша, Вы сможете выбрать ОДНУ игрушку из выпавших вариантов");
        for (int i = 0; i < 3; i++) {
            Toy prize = new ToyService().rafflePrizes();
            if (prize != null) {
                prize.setQuantityInStock(1);
                prize.setDropFrequency(0.0);

                new FileService().dataWriter("P", prize, "A");
                new FileService().dataWriter("D", prize, "A");

            } else
                continue;
        }
    }

    // запись полученных призов в файл и корректировка остатков на складе
    public void prizesReceived() {
        Toy prize = new ToyService().receivingPrize();
        new FileService().dataWriter("R", prize, "A");
        clearFile("P");

        List<Toy> toys = new FileService().dataReader("S");

        clearFile("S");
        
        for (Toy item : toys) {
            if (prize.getId() == item.getId()) {
                item.setQuantityInStock(item.getQuantityInStock() - 1);
            }
            new FileService().dataWriter("S", item, "A");

        }
    }

    // обновления статистики игрушек на складе после розыгрыша
    public void changeDataWriter() {
        List<Toy> toys = new FileService().dataReader("S");
        List<Toy> statistics = new ToyService().toyDropStats();

        clearFile("S");

        for (Toy item : toys) {
            for (Toy el : statistics) {
                if (el.getId() == item.getId()) {
                    item.setDropFrequency(el.getDropFrequency());
                } else
                    continue;
            }
            new FileService().dataWriter("S", item, "A");
        }
    }

    // обновление статистики выпадения игрушек
    public void toyDropStatsWrite() {
        List<Toy> newStatistics = new ToyService().toyDropStats();
        clearFile("D");
        for (Toy item : newStatistics) {
            new FileService().dataWriter("D", item, "A");
        }
    }

    // просмотр данных
    public void showData(String choiceFile){
        List<Toy> data = new FileService().dataReader(choiceFile);
        for (Toy item : data) {
            System.out.println(item);
        } 
    }

}
