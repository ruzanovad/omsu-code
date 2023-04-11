import java.util.*;
import java.util.function.*;

import static java.lang.Math.max;

public class StreamApiDemo extends LambdaDemo {
    public static final Function<List<Object>, List<Object>> deleteNull = a ->
            a.stream().filter(Objects::nonNull).toList();
    public static final Function<Set<Integer>, Integer> countPositive = a ->
            a.stream().mapToInt(b -> b <= 0 ? 0 : 1).sum();
    public static final Function<List<Object>, List<Object>> getLastThree = a ->
            a.stream().skip(max(a.size() - 3, 0)).limit(3).toList();
    public static final Function<List<Integer>, Integer> getfirstEven = a ->
            a.stream().filter(b -> b % 2 == 0).findFirst().orElse(null);
    public static final Function<int[], List<Integer>> getSquares = a ->
            Arrays.stream(a).map(b -> b * b).boxed().distinct().toList();
    public static final Function<List<String>, List<String>> sortNonEmpty = a ->
            a.stream().filter(b -> !"".equals(b)).sorted().toList();
    public static final Function<Set<String>, List<String>> sortReverse =
            a -> a.stream().sorted(Comparator.reverseOrder()).toList();
    public static final Function<Set<Integer>, Integer> getSumSquares = a ->
            a.stream().mapToInt(b -> b * b).sum();
    public static final Function<Collection<? extends Human>, Integer> getMaxAge = a ->
            a.stream().mapToInt(Human::getAge).max().orElse(0);
    public static final Function<Collection<? extends Human>, Collection<? extends Human>> uniqueSort = a ->
            a.stream().sorted(Comparator.comparing(Human::getGender).thenComparing(Human::getAge)).toList();
}