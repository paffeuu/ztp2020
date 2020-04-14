package ztp.lista3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    private String name;
    private List<City> cities;

    public Country() {
        this.cities = new ArrayList<>();
    }

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

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}
