import java.util.Arrays;
import java.util.Objects;

public class Goods {
    private String description;
    private IFoldable[] products;

    public Goods(String description, IFoldable[] products) {
        this.description = description;
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IFoldable[] getProducts() {
        return products;
    }

    public void setProducts(IFoldable[] products) {
        this.products = products;
    }

    public double getWeight() {
        double weight = 0;
        for (IFoldable p :
                products) {
            weight += p.getGrossWeight();
        }
        return weight;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "description='" + description + '\'' +
                ", products=" + Arrays.toString(products) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(description, goods.description) && Arrays.equals(products, goods.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(description);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }
}
