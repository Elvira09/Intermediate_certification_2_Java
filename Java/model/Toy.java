package model;

import java.util.List;
import util.FileService;

public class Toy implements Comparable<Toy>{
    private Integer id;
    private String toy;
    private Integer quantityInStock;
    private Double dropFrequency;

    public Toy(Integer id, String toy, Integer quantityInStock, Double dropFrequency) {
        this.id = id;
        this.toy = toy;
        this.quantityInStock = quantityInStock;
        this.dropFrequency = dropFrequency;
    }

    public Integer getId() {
        return id;
    }

    public String getToy() {
        return toy;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public Double getDropFrequency() {
        return dropFrequency;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToy(String toy) {
        this.toy = toy;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setDropFrequency(Double dropFrequency) {
        this.dropFrequency = dropFrequency;
    }

    // формирование следующего id
    public static Integer newId(String choiceFile) {
        List<Toy> dataReader = new FileService().dataReader(choiceFile);
        return dataReader.size() + 1;
    }

    @Override
    public String toString() {
        return getId() + " ; " + getToy() + " ; " + getQuantityInStock() + " ; " + getDropFrequency();
    }

    @Override
    public int compareTo(Toy o) {
        return this.id - o.getId();
    }

    @Override
    public boolean equals(Object obj) {
        Toy t = (Toy)obj;
        return this.id == t.id
                && this.toy == t.toy
        ;
    }

}
