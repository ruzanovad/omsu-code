import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CollectionsDemoTest {

    List<Human> h;

    @Before
    public void setUp() {
        h = new ArrayList<>();

        h.add(new Human("Пушкин", "Александр", "Сергеевич", 30));
        h.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));
        h.add(new Human("Белинский", "Виссарион", "Григорьевич", 29));
        h.add(new Human("Гейне", "Эдуард", "", 20));
        h.add(new Human("Липшиц", "Рудольф", "", 60));
        h.add(new Student("Гегель", "Георг", "", 61, "Философский"));
        h.add(new Human("Маркс", "Карл", "", 61));
        h.add(new Human("Ковалевская", "Софья", "Васильевна", 40));
        h.add(new Human("Лобачевский", "Николай", "Иванович", 61));
        h.add(new Human("Пушкин", "Лев", "Сергеевич", 30));
    }
    @Test
    public void testBeginsWith() {
        List<String> s = new ArrayList<>();
        s.add("Hello");
        s.add("Hi");
        s.add("Hatred");
        s.add("Algorithm");
        s.add("Binary search");
        assertEquals(3, CollectionsDemo.beginsWith(s, 'H'));
        s.remove(0);
        s.remove(3);
        assertEquals(2, CollectionsDemo.beginsWith(s, 'H'));
        s.add("Bell");
        assertEquals(1, CollectionsDemo.beginsWith(s, 'B'));

        List<String> d = new ArrayList<>();
        d.add(null);
        d.add("ss");
        d.add("hs");
        d.add("");
        assertEquals(1, CollectionsDemo.beginsWith(d, 's'));
    }
    @Test
    public void testGetNamesakes() {
        assertEquals(List.of(
                        new Human("Пушкин", "Лев", "Сергеевич", 30)),
                CollectionsDemo.getNamesakes(h,
                        new Human("Пушкин", "Александр", "Сергеевич", 30)));
    }

    @Test
    public void testDeleteHuman() {
        List<Human> h = new ArrayList<>();
        List<Human> z = new ArrayList<>();
        h.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));
        h.add(new Human("Липшиц", "Рудольф", "", 60));
        h.add(new Human("Лобачевский", "Николай", "Иванович", 61));

        z.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));
        z.add(new Human("Липшиц", "Рудольф", "", 60));

        List<Human> c = CollectionsDemo.deleteHuman(h,
                new Human("Лобачевский", "Николай", "Иванович", 61));
        assertEquals(z, c);

        h.get(0).setPatronym("");
        assertNotEquals(h.get(0), c.get(0));
    }

    @Test
    public void testDisjointSet() {
        // Список Arrays.ArrayList неизменяемый
        Set<Integer> integerSet = new HashSet<>();
        List<Set<Integer>> setSystem = new ArrayList<>();
        List<Set<Integer>> expected = new ArrayList<>();
        integerSet.add(1);
        integerSet.add(-1);
        integerSet.add(2);

        setSystem.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        setSystem.add(new HashSet<>(Arrays.asList(-2, 2, 3)));
        setSystem.add(new HashSet<>(Arrays.asList(-1, 2, 6)));
        setSystem.add(new HashSet<>(Arrays.asList(1, -444, 23, 23, 4, 4)));
        setSystem.add(new HashSet<>(List.of(-100)));
        setSystem.add(new HashSet<>(Arrays.asList(-2, -444, 23, 23, 4, 4)));

        expected.add(new HashSet<>(List.of(-100)));
        expected.add(new HashSet<>(Arrays.asList(-2, -444, 23, 23, 4, 4)));
        assertEquals(expected, CollectionsDemo.disjointSet(setSystem, integerSet));
    }
    @Test
    public void testGetMaxAgeSet() {
        Set<Human> x = new HashSet<>();
        x.add(new Human("Лобачевский", "Николай", "Иванович", 61));
        x.add(new Student("Гегель", "Георг", "", 61, "Философский"));

        x.add(new Human("Маркс", "Карл", "", 61));
        assertEquals(x, CollectionsDemo.getMaxAgeSet(h));
    }

    @Test
    public void testGetSortedByName() {
        Set<Human> x = new HashSet<>();
        x.add(new Human("Лобачевский", "Николай", "Иванович", 61));
        x.add(new Student("Гегель", "Георг", "", 61, "Философский"));
        x.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));

        List<Human> h = new ArrayList<>();
        h.add(new Student("Гегель", "Георг", "", 61, "Философский"));
        h.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));
        h.add(new Human("Лобачевский", "Николай", "Иванович", 61));
        assertEquals(h, CollectionsDemo.getSortedByName(x));
    }
    @Test
    public void testGetHumansFromSet() {
        Map<Integer, Human> integerHumanMap = new HashMap<>();

        integerHumanMap.put(14532, new Human("Пушкин", "Александр", "Сергеевич", 30));
        integerHumanMap.put(73241, new Human("Пушкин", "Лев", "Сергеевич", 30));
        integerHumanMap.put(23901, new Human("Липшиц", "Рудольф", "", 60));
        integerHumanMap.put(63891, new Human("Маркс", "Карл", "", 61));

        Set<Integer> integers = new HashSet<>(Arrays.asList(14542, 24112, 23901, 73240, 63891));

        Set<Human> humans = new HashSet<>();

        humans.add(new Human("Липшиц", "Рудольф", "", 60));
        humans.add(new Human("Маркс", "Карл", "", 61));

        assertEquals(humans, CollectionsDemo.getHumansFromSet(integerHumanMap, integers));
    }

    @Test
    public void testGetAdults() {
        Map<Integer, Human> integerHumanMap = new HashMap<>();

        integerHumanMap.put(14532, new Human("Пушкин", "Александр", "Сергеевич", 17));
        integerHumanMap.put(78893, new Human("Ковалевская", "Софья", "Васильевна", 47));
        integerHumanMap.put(73241, new Human("Пушкин", "Лев", "Сергеевич", 13));
        integerHumanMap.put(23901, new Human("Липшиц", "Рудольф", "", 43));
        integerHumanMap.put(63891, new Human("Маркс", "Карл", "", 31));

        List<Integer> list = new ArrayList<>(Arrays.asList(63891, 78893, 23901));

        assertEquals(list, CollectionsDemo.getAdults(integerHumanMap));
    }

    @Test
    public void testGetIntegerIntegerMap() {
        Map<Integer, Human> integerHumanMap = new HashMap<>();

        integerHumanMap.put(14532, new Human("Пушкин", "Александр", "Сергеевич", 17));
        integerHumanMap.put(78893, new Human("Ковалевская", "Софья", "Васильевна", 47));
        integerHumanMap.put(73241, new Human("Пушкин", "Лев", "Сергеевич", 13));
        integerHumanMap.put(23901, new Human("Липшиц", "Рудольф", "", 43));
        integerHumanMap.put(63891, new Human("Маркс", "Карл", "", 31));

        Map<Integer, Integer> integerIntegerMap= new HashMap<>();

        integerIntegerMap.put(14532, 17);
        integerIntegerMap.put(78893, 47);
        integerIntegerMap.put(73241, 13);
        integerIntegerMap.put(23901, 43);
        integerIntegerMap.put(63891, 31);

        assertEquals(integerIntegerMap, CollectionsDemo.getIntegerIntegerMap(integerHumanMap));
    }

    @Test
    public void testGetIntegerListMap() {
        Set<Human> h = new HashSet<>();

        h.add(new Human("Пушкин", "Александр", "Сергеевич", 30));
        h.add(new Human("Гончарова", "Екатерина", "Николаевна", 30));
        h.add(new Human("Белинский", "Виссарион", "Григорьевич", 29));
        h.add(new Human("Гейне", "Эдуард", "", 20));
        h.add(new Human("Липшиц", "Рудольф", "", 60));
        h.add(new Human("Гегель", "Георг", "", 61));
        h.add(new Human("Маркс", "Карл", "", 61));
        h.add(new Human("Ковалевская", "Софья", "Васильевна", 40));
        h.add(new Human("Лобачевский", "Николай", "Иванович", 61));
        h.add(new Human("Пушкин", "Лев", "Сергеевич", 30));

        Map<Integer, List<Human>> integerListMap = new HashMap<>();

        integerListMap.put(30, Arrays.asList(
                new Human("Пушкин", "Александр", "Сергеевич", 30),
                new Human("Гончарова", "Екатерина", "Николаевна", 30),
                new Human("Пушкин", "Лев", "Сергеевич", 30)));
        integerListMap.put(29, List.of(new Human("Белинский", "Виссарион", "Григорьевич", 29)));
        integerListMap.put(20, List.of(new Human("Гейне", "Эдуард", "", 20)));
        integerListMap.put(60, List.of(new Human("Липшиц", "Рудольф", "", 60)));
        integerListMap.put(61, Arrays.asList(
                new Human("Гегель", "Георг", "", 61),
                new Human("Лобачевский", "Николай", "Иванович", 61),
                new Human("Маркс", "Карл", "", 61)));
        integerListMap.put(40, List.of(new Human("Ковалевская", "Софья", "Васильевна", 40)));

        assertEquals(integerListMap, CollectionsDemo.getIntegerListMap(h));
    }

    @Test
    public void testGetMapByAge(){
        Set<Human> humans = new HashSet<>();
        humans.add(new Human("Алексеев", "Алексей", "Алексеевич", 15));
        humans.add(new Human("Бобров", "Алексей", "Алексеевич", 15));
        humans.add(new Human("Алешин", "Алексей", "Петрович", 15));

        humans.add(new Human("Васильев", "Антон", "Александрович", 5));
        humans.add(new Human("Васильев", "Василий", "Александрович", 5));
        humans.add(new Human("Васильева", "Наталья", "Александровна", 6));

        humans.add(new Human("Соловьев", "Михаил", "Андреевич", 45));
        humans.add(new Human("Соловьева", "Раиса", "Павловна", 67));

        humans.add(new Human("Ли", "Никита", "Александрович", 6));

        Map<Integer, Map<Character, List<Human>>> integerMapMap = CollectionsDemo.getMapByAge(humans);

        Map<Character, List<Human>> characterListMap1 = new HashMap<>();
        characterListMap1.put('А', new ArrayList<>());
        characterListMap1.put('Б', new ArrayList<>());

        characterListMap1.get('А').add(new Human("Алешин", "Алексей", "Петрович", 15));
        characterListMap1.get('А').add(new Human("Алексеев", "Алексей", "Алексеевич", 15));
        characterListMap1.get('Б').add(new Human("Бобров", "Алексей", "Алексеевич", 15));

        Map<Character, List<Human>> characterListMap2 = new HashMap<>();

        characterListMap2.put('В', new ArrayList<>());

        characterListMap2.get('В').add(new Human("Васильев", "Василий", "Александрович", 5));
        characterListMap2.get('В').add(new Human("Васильев", "Антон", "Александрович", 5));

        Map<Character, List<Human>> characterListMap3 = new HashMap<>();

        characterListMap3.put('В', new ArrayList<>());
        characterListMap3.put('Л', new ArrayList<>());

        characterListMap3.get('В').add(new Human("Васильева", "Наталья", "Александровна", 6));
        characterListMap3.get('Л').add(new Human("Ли", "Никита", "Александрович", 6));

        Map<Character, List<Human>> characterListMap4 = new HashMap<>();

        characterListMap4.put('С', new ArrayList<>());

        characterListMap4.get('С').add(new Human("Соловьев", "Михаил", "Андреевич", 45));

        Map<Character, List<Human>> characterListMap5 = new HashMap<>();

        characterListMap5.put('С', new ArrayList<>());

        characterListMap5.get('С').add(new Human("Соловьева", "Раиса", "Павловна", 67));

        Map<Integer, Map<Character, List<Human>>> expected = new HashMap<>();
        expected.put(15, characterListMap1);
        expected.put(5, characterListMap2);
        expected.put(6, characterListMap3);
        expected.put(45, characterListMap4);
        expected.put(67, characterListMap5);

        assertEquals(expected, integerMapMap);
    }


}