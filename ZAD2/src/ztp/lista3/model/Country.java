package ztp.lista3.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    private List<City> cities;

    public Country(String name) {
        this.name = name;
    }

    public Country(Country country) {
        this(country.name);
        this.cities = new ArrayList<>();
        for (City city : country.cities) {
            City newCity = new City(city);
            this.cities.add(newCity);
        }
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "\nCountry{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
