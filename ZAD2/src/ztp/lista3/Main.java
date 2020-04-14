package ztp.lista3;

import ztp.lista3.deepcopy.ApacheCommonsCopier;
import ztp.lista3.deepcopy.CopyConstructorCopier;
import ztp.lista3.deepcopy.CountryCopier;
import ztp.lista3.deepcopy.SetterCopier;
import ztp.lista3.model.Country;
import ztp.lista3.utils.RandomDataGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        List<Country>[] countriesArray = new List[5];
        CountryCopier countryCopier;
        List<Country> copiedCountries;

        countriesArray[0] = randomDataGenerator.generateRandomDataSet(2, 5, 10);
        countriesArray[1] = randomDataGenerator.generateRandomDataSet(5, 10, 20);
        countriesArray[2] = randomDataGenerator.generateRandomDataSet(10, 20, 50);
//        countriesArray[3] = randomDataGenerator.generateRandomDataSet(20, 50, 100);
//        countriesArray[4] = randomDataGenerator.generateRandomDataSet(50, 100, 200);

//        for (int i = 0; i < 5; i++) {
        for (int i = 0; i < 3; i++) {
            System.out.println("======================================");
            System.out.println("Generating random " + (int) (10 * Math.pow(10.0, i + 1.0)) + " objects");
            System.out.println("Deep copy in three different ways:\n");

            countryCopier = new CopyConstructorCopier();
            System.out.println("First approach: " + countryCopier.getClass().getSimpleName());
            long firstStart = System.currentTimeMillis();
            copiedCountries = countryCopier.copyCountryList(countriesArray[i]);
            long firstEnd = System.currentTimeMillis();
            long firstTime = firstEnd - firstStart;
            System.out.println("This approach took " + firstTime + " miliseconds.\n");

            countryCopier = new SetterCopier();
            System.out.println("Second approach: " + countryCopier.getClass().getSimpleName());
            long secondStart = System.currentTimeMillis();
            copiedCountries = countryCopier.copyCountryList(countriesArray[i]);
            long secondEnd = System.currentTimeMillis();
            long secondTime = secondEnd - secondStart;
            System.out.println("This approach took " + secondTime + " miliseconds.\n");

            countryCopier = new ApacheCommonsCopier();
            System.out.println("Third approach: " + countryCopier.getClass().getSimpleName());
            long thirdStart = System.currentTimeMillis();
            copiedCountries = countryCopier.copyCountryList(countriesArray[i]);
            long thirdEnd = System.currentTimeMillis();
            long thirdTime = thirdEnd - thirdStart;
            System.out.println("This approach took " + thirdTime + " miliseconds.\n");
        }



    }

}
