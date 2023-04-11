import java.util.*;
import java.util.function.DoubleToLongFunction;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @org.junit.jupiter.api.Test
    void getElemsByName() {
        List<Data> l = new ArrayList<>();
        l.add(null);
        List<Data> g = new ArrayList<>(List.of(new Data("name", 4), new Data("nam", 6),
                new Data("", -32)));
        String s = "name";

        Collection<Data> k = new ArrayList<>();
        Collection<Data> m = new ArrayList<>(List.of(new Data("name", 4)));

        assertEquals(k, Service.getElemsByName(l, s));
        assertEquals(m, Service.getElemsByName(g, s));

    }

    @org.junit.jupiter.api.Test
    void getElemsByLevel() {

        List<Data> d = new ArrayList<>(List.of(new Data("", 2), new Data("", -2.3),
                new Data("", -1), new Data(" ", -1), new Data("", -5)));
        Collection<Data> g = new ArrayList<>(List.of(new Data("", 2), new Data("", -1),
                new Data(" ", -1)));

        assertEquals(g, Service.getElemsByLevel(d, 2.0));

        List<Data> b = new ArrayList<>();
        d.add(null);
        Collection<Data> a = new ArrayList<>();
        assertEquals(a, Service.getElemsByLevel(b, 0));
    }

    @org.junit.jupiter.api.Test
    void getValuesFromSet() {
        List<Data> d = new ArrayList<>(List.of(new Data("1", 2), new Data("2", -2.3),
                new Data("3", -1), new Data("4", -1), new Data("5", -5),
                new Data("1", 6)));
        Set<String> s = new HashSet<>(Set.of("1", "4", "5"));
        Set<Double> r = new HashSet<>(Set.of(2., 6., -1., -5.));

        assertEquals(r, Service.getValuesFromSet(d, s));
    }

    @org.junit.jupiter.api.Test
    void getNamesFromPositive() {
        List<Data> d = new ArrayList<>(List.of(new Data("1", 2), new Data("2", -2.3),
                new Data("3", -1), new Data("1", -1), new Data("5", 5),
                new Data("1", 6), new Data("pos", 0.3)));

        Set<String> ss = new TreeSet<>(Set.of("1", "pos", "5"));
        String[] s = new String[3];
        int j = 0;
        for (var i: ss){
            s[j] = i;
            j+=1;
        }
        assertArrayEquals(s, Service.getNamesFromPositive(d));
    }

    @org.junit.jupiter.api.Test
    void union() {
        List<Set<Data>> s = new ArrayList<>();
        for (int i = 0; i < 5; ++i){
            Set<Data> s1 = new HashSet<>();
            for (int j = 0; j < 3; ++j){
                s1.add(new Data("a", i+j));
            }
            s.add(s1);
        }
        // 0 1 2
        // 1 2 3
        // ...
        // 4 5 6
        Set<Data> fin = new HashSet<>();
        for (int i = 0; i < 7; ++i){
            fin.add(new Data("a", i));
        }
        assertEquals(fin, Service.union(s));

        List<Set<Integer>> b = new ArrayList<>();
        b.add(null);
        b.add(Set.of(1, 2));
        Set<Integer> c = new HashSet<>();
        c.add(null);
        c.add(5);
        b.add(c);
        Set<Integer> f = new HashSet<>();
        f.add(null);
        f.add(1);
        f.add(2);
        f.add(5);
        assertEquals(f, Service.union(b));
    }

    @org.junit.jupiter.api.Test
    void intersection() {
        List<Set<Data>> s = new ArrayList<>();
        for (int i = 0; i < 3; ++i){
            Set<Data> s1 = new HashSet<>();
            for (int j = 0; j < 5; ++j){
                s1.add(new Data("a", i+j));
            }
            s.add(s1);
        }
        // 0 1 2 3 4
        // 1 2 3 4 5
        // 2 3 4 5 6
        Set<Data> fin = new HashSet<>();
        for (int i = 2; i < 5; ++i){
            fin.add(new Data("a", i));
        }
        assertEquals(fin, Service.intersection(s));

        List<Set<Integer>> b = new ArrayList<>();
        Set<Integer> v = new HashSet<>();
        v.add(null);
        v.add(1);
        b.add(v);
        Set<Integer> g =new HashSet<>(Set.of(1, 2));
        g.add(null);
        b.add(g);

        Set<Integer> c = new HashSet<>();
        c.add(null);
        b.add(c);
        Set<Integer> f = new HashSet<>();
        f.add(null);
        Set<Integer> m = Service.intersection(b);
        assertEquals(f, m);
    }

    @org.junit.jupiter.api.Test
    void getMaxSets() {
        List<Set<Data>> s = new ArrayList<>();
        for (int i = 0; i < 3; ++i){
            Set<Data> s1 = new HashSet<>();
            for (int j = 0; j < 2; ++j){
                s1.add(new Data("a", i+j));
            }
            s.add(s1);
        }
        for (int i = 0; i < 2; ++i){
            Set<Data> s1 = new HashSet<>();
            for (int j = 0; j < 5; ++j){
                s1.add(new Data("a", i+j));
            }
            s.add(s1);
        }
        List<Set<Data>> m = new ArrayList<>();
        for (int i = 0; i < 2; ++i){
            Set<Data> s1 = new HashSet<>();
            for (int j = 0; j < 5; ++j){
                s1.add(new Data("a", i+j));
            }
            m.add(s1);
        }
        assertEquals(m, Service.getMaxSets(s));
    }
}