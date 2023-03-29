package util;


public class PathFile {
    private String pathStock ;
    private String pathPrize ;
    private String prizesReceived ;
    private String toyDropStats ;

    public PathFile() {
        this.pathStock = "/Users/GB/Intermediate_certification_2_Java/Java/data/stockToys.txt";
        this.pathPrize = "/Users/GB/Intermediate_certification_2_Java/Java/data/prizeToys.txt";
        this.prizesReceived = "/Users/GB/Intermediate_certification_2_Java/Java/data/prizesReceived.txt";
        this.toyDropStats = "/Users/GB/Intermediate_certification_2_Java/Java/data/toyDropStats.txt";        
    }


    // путь к файлу stockToys (склада)
    public String getPathStock() {
        return this.pathStock;
    }
    
    // путь к файлу prizeToys (призы)
    public String getPathPrize() {
        return this.pathPrize;
    }

    // путь к файлу prizesReceived (полученные призы)
    public String getPrizesReceived() {
        return this.prizesReceived;
    }

    // путь к файлу toyDropStats (статистика выпадения)
    public String getToyDropStats() {
        return this.toyDropStats;
    }

    
}

