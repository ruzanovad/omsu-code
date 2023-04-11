import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PackagedProductSet implements IPackaged{
    private String name;
    private Packaging packaging;
    private IPackaged[] products;

    public PackagedProductSet(Packaging packaging, IPackaged[] products, String name) {
        this.packaging = packaging;
        this.products = products;
        this.name = name;
    }

    @Override
    public double getNetWeight() {
        double weight = 0;
        for (IPackaged p :
                products) {
            weight += p.getNetWeight();
        }
        return weight;
    }

    @Override
    public double getGrossWeight() {
        double weight = packaging.getWeight();
        for (IPackaged p :
                products) {
            weight += p.getGrossWeight();
        }
        return weight;
    }


    public String getName() {
        return name;
    }

    @Override
    public IFoldable[] getArray() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IPackaged[] getProducts() {
        return products;
    }

    public void setProducts(IPackaged[] products) {
        this.products = products;
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
    public String toString() {
        return "PackagedProductSet{" + "name='" + name + '\'' +
                ", packaging=" + packaging +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
