package ztp.lista3.model;

import java.io.Serializable;

public class Inhabitant implements Serializable {
    private String firstName;
    private String lastName;

    public Inhabitant() {}

    public Inhabitant(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Inhabitant(Inhabitant inhabitant) {
        this(inhabitant.firstName, inhabitant.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Inhabitant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
