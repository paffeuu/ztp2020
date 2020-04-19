package ztp.lista3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class City implements Serializable {
    private String name;
    private List<Inhabitant> inhabitants;

    public City() {
        this.inhabitants = new ArrayList<>();
    }

    public City(String name) {
        this.name = name;
        this.inhabitants = new ArrayList<>();
    }

    public City(City city) {
        this(city.name);
        this.inhabitants = new ArrayList<>();
        for (Inhabitant inhabitant : city.inhabitants) {
            Inhabitant newInhabitant = new Inhabitant(inhabitant);
            this.inhabitants.add(newInhabitant);
        }
    }

    public String getName() {
        return name;
    }

    public List<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInhabitants(List<Inhabitant> inhabitants) {
        this.inhabitants = inhabitants;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", inhabitants=" + inhabitants +
                '}';
    }
}
