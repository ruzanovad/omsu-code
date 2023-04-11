import java.lang.reflect.Array;
import java.util.Arrays;

public class ComparatorDemo {
    public static Product[] sortGoods(Product[] products, ProductComparator comparator){
        Arrays.sort(products, comparator);
        return products;
    }
}
