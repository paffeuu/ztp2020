package ztp.lista3.model;

import java.util.HashSet;
import java.util.Set;

public class City {
    private String name;
    private Set<Inhabitant> inhabitants;

    public City() {
        this.inhabitants = new HashSet<>();
    }

    public City(String name) {
        this.name = name;
        this.inhabitants = new HashSet<>();
    }

    public City(City city) {
        this(city.name);
        this.inhabitants = new HashSet<>();
        for (Inhabitant inhabitant : city.inhabitants) {
            Inhabitant newInhabitant = new Inhabitant(inhabitant);
            this.inhabitants.add(newInhabitant);
        }
    }

    public String getName() {
        return name;
    }

    public Set<Inhabitant> getInhabitants() {
        return inhabitants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInhabitants(Set<Inhabitant> inhabitants) {
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
