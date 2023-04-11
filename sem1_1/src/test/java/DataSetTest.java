import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataSetTest {

    DataSet m = new DataSet();
    DataSet n = new DataSet();

    @BeforeEach
    void setUp() {
        m.add(new Data("1", 2));
        m.add(new Data("4", -1));
        m.add(new Data("1", 6));
        m.add(new Data("3", -1));

        n.add(new Data("1", 2));
        n.add(new Data("a", -39));
        n.add(new Data("c", 23));
        n.add(new Data("v", 0));
    }

//    @Test
//    void add() {
//    }

    @Test
    void iterator() {
        {
            List<Data> iterate = new ArrayList<>();
            List<Data> expected = new ArrayList<>(List.of(new Data("1", 2), new Data("4", -1), new Data("1", 6), new Data("3", -1)));

            for (var x : m) {
                iterate.add(x);
            }
            assertEquals(expected, iterate);
        }
        {
            List<Data> iterate = new ArrayList<>();
            List<Data> expected = new ArrayList<>(List.of(new Data("1", 2), new Data("a", -39), new Data("c", 23), new Data("v", 0)));

            for (var x : n) {
                iterate.add(x);
            }
            assertEquals(expected, iterate);
        }
        {
//            List<Data> expected = new ArrayList<>();
//
//            expected.add(new Data("", 1));
//            expected.add(new Data("s", 2));
//
//            List<Data> iterate = new ArrayList<>();
//            for (var x : n) {
//                iterate.add(x);
//            }
//            assertEquals(expected, iterate);
        }
    }

    @Test
    void sort() {
        DataSet a = new DataSet();
        a.add(new Data("ae", 32423));
        a.add(new Data( "cb", 23));
        a.add(new Data("z", 1));
        a.add(new Data("ca", -3.2));
        a.add(new Data("ad", -3));


        DataSet b = new DataSet();
        b.add(new Data("ad", -3));
        b.add(new Data("ae", 32423));
        b.add(new Data("ca", -3.2));
        b.add(new Data( "cb", 23));
        b.add(new Data("z", 1));
        a.sort();

        assertEquals(b, a);
    }
}