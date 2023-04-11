import java.util.Objects;

public class Data {
    private String name;
    private double value;

    public Data(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Double.compare(data.value, value) == 0 && Objects.equals(name, data.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
