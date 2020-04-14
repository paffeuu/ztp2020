package ztp.lista3.model;

import java.util.HashSet;
import java.util.Set;

public class City {
    private String name;
    private Set<Inhabitant> inhabitants;

    public City(String name) {
        this.name = name;
        this.inhabitants = new HashSet<>();
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
