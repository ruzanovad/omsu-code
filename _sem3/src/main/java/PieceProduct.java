import java.util.Objects;

public class PieceProduct extends Product implements IPiece {
    protected double weight;

    public PieceProduct(String name, String desc, double weight) {
        this.name = name;
        this.description = desc;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getGrossWeight(){ return weight;}

    @Override
    public IFoldable[] getArray() {
        return new IFoldable[0];
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PieceProduct that = (PieceProduct) o;
        return Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public String toString() {
        return "PieceProduct{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}

