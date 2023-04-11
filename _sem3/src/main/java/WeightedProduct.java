public class WeightedProduct extends Product implements IWeighted{
    public WeightedProduct(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    @Override
    public String toString() {
        return "WeightedProduct{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public double getGrossWeight() {
        return 0;
    }

    @Override
    public IFoldable[] getArray() {
        return new IFoldable[0];
    }
}
