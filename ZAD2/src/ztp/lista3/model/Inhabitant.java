package ztp.lista3.model;

public class Inhabitant {
    private String firstName;
    private String lastName;

    public Inhabitant(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Inhabitant(Inhabitant inhabitant) {
        this(inhabitant.firstName, inhabitant.lastName);
    }

    @Override
    public String toString() {
        return "Inhabitant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
