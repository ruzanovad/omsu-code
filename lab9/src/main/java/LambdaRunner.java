import java.util.function.BiPredicate;
import java.util.function.Function;

public class LambdaRunner {

    public static <T, V> V oneParam(T value, Function<T, V> lambda) {
        return lambda.apply(value);
    }

    public static <T, V> boolean twoParams(T value1, V value2, BiPredicate<T, V> lambda) {
        return lambda.test(value1, value2);
    }

    public static <T extends Human> boolean uniqueParam(T h1, T h2, T h3, int maxAge, IFunction<T> lambda) {
        return lambda.apply(h1, h2, h3, maxAge);
    }
}
