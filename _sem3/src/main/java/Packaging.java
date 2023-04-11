import java.util.Objects;

public class Packaging {
    private String name;
    private double weight;

    public Packaging(String name, double weight) {
        if (weight < 0) throw new IllegalArgumentException();
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) throw new IllegalArgumentException();
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packaging packaging = (Packaging) o;
        return Double.compare(packaging.weight, weight) == 0 && Objects.equals(name, packaging.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Packaging{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
