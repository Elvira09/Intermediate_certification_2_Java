package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Toy;

public class FileService {
    private String path;
    private Boolean action;

    // выбираем файл для действий
    public String selectFile(String choiceFile) {
        if (choiceFile.equalsIgnoreCase("S")) { // склад
            this.path = new PathFile().getPathStock();
        } else if (choiceFile.equalsIgnoreCase("P")) { // призы
            this.path = new PathFile().getPathPrize();
        } else if (choiceFile.equalsIgnoreCase("R")) { // полученные призы
            this.path = new PathFile().getPrizesReceived();
        } else if (choiceFile.equalsIgnoreCase("D")) { // статистика выпадения
            this.path = new PathFile().getToyDropStats();
        }
        return this.path;
    }

    // выбираем действие с файлом O -перезапись / A -добавление
    public Boolean fileAction(String choice2) {
        if (choice2.equalsIgnoreCase("O")) { // перезапись
            this.action = false;
        } else if (choice2.equalsIgnoreCase("A")) { // добавление
            this.action = true;
        }
        return this.action;
    }

    // чтение файла
    public List<Toy> dataReader(String choiceFile) {
        String path = selectFile(choiceFile);
        List<Toy> toys = new ArrayList<Toy>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                var item = line.split(";");
                Toy toy = new Toy(
                        Integer.valueOf(item[0].trim()),
                        item[1].trim(),
                        Integer.valueOf(item[2].trim()),
                        Double.parseDouble(item[3].trim()));
                toys.add(toy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (toys.size() == 0)
            System.out.println("Файл пустой ");
        return toys;
    }

    // запись файла
    public void dataWriter(String choiceFile, Toy toy, String choiceAction) {
        String path = selectFile(choiceFile);
        boolean action = fileAction(choiceAction);
        try (FileWriter writer = new FileWriter(path, action)) {
            writer.write(toy.toString());
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
