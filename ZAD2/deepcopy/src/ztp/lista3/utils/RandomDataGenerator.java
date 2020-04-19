package ztp.lista3.utils;

import ztp.lista3.model.City;
import ztp.lista3.model.Country;
import ztp.lista3.model.Inhabitant;

import java.util.*;

public class RandomDataGenerator {
    private String[] countries = new String[] {
            "Polska", "Niemcy", "Włochy", "Francja", "Hiszpania", "Rosja", "Białoruś", "Czechy",
            "USA", "Indie", "Chiny", "Dania", "Norwegia", "Szwecja", "Malta", "Islandia", "Belgia"};

    private String[] cities = new String[] {
            "Warszawa", "Kraków", "Wrocław", "Gdańsk", "Poznań", "Szczecin", "Katowice", "Lublin",
            "Berlin", "Paryż", "Londyn", "Nowy Jork", "Oslo", "Glasgow", "Reykjavik", "Belgrad", "Moskwa"};

    private String[] firstNames = new String[] {
            "Marek", "Andrzej", "Jarek", "Wiesław", "Miłosz", "Tomek", "Jakub", "Aleksander",
            "Kasia", "Ala", "Zuzia", "Paulina", "Justyna", "Marta", "Kaja", "Karolina", "Ola"};

//    private String[] lastNames = new String[] {
//            "Nowak", "Leśniak", "Kozioł", "Minkiewicz", "Dubniewicz", "Iwańczyk", "Masiak", "Gajor",
//            "Miniak", "Tuleja", "Dworczyk", "Maślakiewicz", "Marnicz", "Tałataj", "Grubiak", "Dolczyk"};

    private Random random;

    public RandomDataGenerator() {
        this.random = new Random();
    }

    public List<Country> generateRandomDataSet(int countryNr, int cityNr, int inhabitantNr) {
        List<Country> countryList = generateRandomCountries(countryNr);
        for (Country country : countryList) {
            List<City> cityList = generateRandomCities(cityNr);
            country.setCities(cityList);
            for (City city : cityList) {
                List<Inhabitant> inhabitantList = generateRandomInhabitants(inhabitantNr);
                city.setInhabitants(inhabitantList);
            }
        }
        return countryList;
    }
    
    private List<Country> generateRandomCountries(int number) {
        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Country country = new Country(countries[random.nextInt(countries.length)]);
            countryList.add(country);
        }
        return countryList;
    }

    private List<City> generateRandomCities(int number) {
        List<City> cityList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            City city = new City(cities[random.nextInt(cities.length)]);
            cityList.add(city);
        }
        return cityList;
    }

    private List<Inhabitant> generateRandomInhabitants(int number) {
        List<Inhabitant> inhabitantList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            char[] lastNameCharArr = new char[random.nextInt(10) + 5];
            lastNameCharArr[0] = (char) (65 + random.nextInt(26));
            for (int j = 1; j < lastNameCharArr.length; j++) {
                lastNameCharArr[j] = (char) (97 + random.nextInt(26));
            }
            Inhabitant inhabitant = new Inhabitant(
                    firstNames[random.nextInt(firstNames.length)],
                    new String(lastNameCharArr));
            inhabitantList.add(inhabitant);
        }
        return inhabitantList;
    }

}
