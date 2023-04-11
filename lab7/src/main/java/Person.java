import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("patronym")
    private final String patronym;
    @JsonProperty("birthDate")
    private final String birthDate;
    public Person(){
        surname = "";
        name = "";
        patronym = "";
        birthDate = "";
    }

    public Person(String surname, String name, String patronym, String birthDate) {
        this.surname = surname;
        this.name = name;
        this.patronym = patronym;
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronym() {
        return patronym;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(surname, person.surname) && Objects.equals(name, person.name) && Objects.equals(patronym, person.patronym) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronym, birthDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(surname);
        sb.append(" ");
        if (!name.isEmpty()){
            sb.append(name.charAt(0));
            sb.append(". ");
        }
        if (!patronym.isEmpty()){
            sb.append(patronym.charAt(0));
            sb.append(".");
        }
        return sb.toString();
    }
}
