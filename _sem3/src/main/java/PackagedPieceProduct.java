import java.util.Objects;

public class PackagedPieceProduct extends PieceProduct implements IPiece, IPackaged{
    protected int number;
    protected Packaging packaging;

    public PackagedPieceProduct(String name, String desc, double weight, int number, Packaging packaging) {
        super(name, desc, weight);
        this.number = number;
        this.packaging = packaging;
    }
    public PackagedPieceProduct(PieceProduct product, int number, Packaging packaging) {
        super(product.name, product.description, product.weight);
        this.number = number;
        this.packaging = packaging;
    }

    @Override
    public double getNetWeight() {
        return this.getWeight() * this.number;
    }

    @Override
    public double getGrossWeight() {
        return this.packaging.getWeight() + getNetWeight();
    }

    @Override
    public IFoldable[] getArray() {
        return new IFoldable[0];
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    @Override
    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PackagedPieceProduct that = (PackagedPieceProduct) o;
        return number == that.number && Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, packaging);
    }

    @Override
    public String toString() {
        return "PackagedPieceProduct{" + "number=" + number +
                ", packaging=" + packaging +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
