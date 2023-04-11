import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("number")
    private final String number;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("boss")
    private final Person boss;
    @JsonProperty("flats")
    private final List<Flat> flats;
    public House(){
        number = "";
        address = "";
        boss = new Person();
        flats = new ArrayList<>();
    }

    public House(String number, String address, Person boss, List<Flat> flats) {
        this.number = number;
        this.address = address;
        this.boss = boss;
        this.flats = flats;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public Person getBoss() {
        return boss;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(number, house.number) && Objects.equals(address, house.address) && Objects.equals(boss, house.boss) && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, address, boss, flats);
    }
}
