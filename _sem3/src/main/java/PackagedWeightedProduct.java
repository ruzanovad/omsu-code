import java.util.Objects;

public class PackagedWeightedProduct extends WeightedProduct implements IWeighted, IPackaged{
    private double weight;
    protected Packaging packaging;

    public PackagedWeightedProduct(String name, String desc, double weight, Packaging packaging){
        super(name, desc);
        this.weight = weight;
        this.packaging = packaging;
    }

    public PackagedWeightedProduct(WeightedProduct product, double weight, Packaging packaging) {
        super(product.name, product.description);
        this.weight = weight;
        this.packaging = packaging;
    }

    public double getNetWeight() {
        return this.weight;
    }
    public double getGrossWeight(){
        return this.packaging.getWeight()+this.weight;
    }

    @Override
    public IFoldable[] getArray() {
        return new IFoldable[0];
    }

    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    @Override
    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PackagedWeightedProduct that = (PackagedWeightedProduct) o;
        return Double.compare(that.weight, weight) == 0 && Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight, packaging);
    }

    @Override
    public String toString() {
        return "PackagedWeightedProduct{" + "weight=" + weight +
                ", packaging=" + packaging +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
