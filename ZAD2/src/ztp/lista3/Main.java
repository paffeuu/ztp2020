package ztp.lista3;

import ztp.lista3.deepcopy.CopyConstructorCopier;
import ztp.lista3.deepcopy.CountryCopier;
import ztp.lista3.deepcopy.SetterCopier;
import ztp.lista3.model.Country;
import ztp.lista3.utils.RandomDataGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        List<Country> countries = randomDataGenerator.generateRandomDataSet(10, 20, 500);
        System.out.println(countries);
        CountryCopier countryCopier = new CopyConstructorCopier();
        System.out.println(System.currentTimeMillis());
        List<Country> copiedCountries = countryCopier.copyCountryList(countries);
        System.out.println(System.currentTimeMillis());
        countryCopier = new SetterCopier();
        System.out.println();
        System.out.println(System.currentTimeMillis());
        copiedCountries = countryCopier.copyCountryList(countries);
        System.out.println(System.currentTimeMillis());
    }

}
