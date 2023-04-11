import java.util.Comparator;
import java.util.Objects;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if (Objects.equals(o1.name, o2.name)){
            return o1.description.compareTo(o2.description);
        }
        else{
            return o1.name.compareTo(o2.name);
        }
    }
}
