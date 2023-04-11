import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("number")
    private final int number;
    @JsonProperty("area")
    private final double area;
    @JsonProperty("people")
    private final List<Person> people;

    public Flat(){
        number = 0;
        area = 0;
        people = new ArrayList<>();
    }

    public Flat(int number, double area, List<Person> people) {
        this.number = number;
        this.area = area;
        this.people = people;
    }

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return area;
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(flat.area, area) == 0 && Objects.equals(people, flat.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, area, people);
    }
}
