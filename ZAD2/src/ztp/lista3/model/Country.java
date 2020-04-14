package ztp.lista3.model;

import java.util.List;

public class Country {
    private String name;
    private List<City> cities;

    public Country(String name) {
        this.name = name;
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
