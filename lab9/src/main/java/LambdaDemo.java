import java.util.Optional;
import java.util.function.*;

import static java.lang.Math.max;

public class LambdaDemo {
    public static final Function<String, Integer> getLength = String::length;
    public static final Function<String, Character> getFirstChar = a -> Optional.ofNullable(a)
            .filter(s -> !s.isEmpty())
            .map(b -> b.charAt(0))
            .orElse(null);

    //    a.matches(".*\\s+.*");
    public static final Function<String, Boolean> noSpaces = a -> a.indexOf(' ') == -1;
    public static final Function<String, Integer> getCountOfWords = a ->
            a.charAt(0) == ',' ? max(
                    a.split(",+").length - 1, 0) : a.split(",+").length;
    public static final Function<? super Human, Integer> getHumanAge = Human::getAge;
    public static final BiPredicate<? super Human, ? super Human> areSurnamesEqual = (a, b) -> (a.getSurname().equals(b.getSurname()));
    public static final Function<? super Human, String> getSNP = a -> String.format("%s %s %s", a.getSurname(),
            a.getName(), a.getPatronym());
    public static final Function<Human, Human> makeOlder = a -> {
        Human h = new Human(a);
        h.setAge(a.getAge() + 1);
        return h;
    };
    public static final IFunction<Human> checkYounger = (a, b, c, d) -> a.getAge() < d && b.getAge() < d && c.getAge() < d;
}

@FunctionalInterface
interface IFunction<T> {
    boolean apply(T a, T b, T c, int maxAge);
}
