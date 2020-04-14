package ztp.lista3;

import ztp.lista3.model.Country;
import ztp.lista3.utils.RandomDataGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        List<Country> countries = randomDataGenerator.generateRandomDataSet(2, 10, 20);
        System.out.println(countries);
    }

}
