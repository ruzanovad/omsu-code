import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DataTest {

    Group g1;
    Group g2;
    Group g3;
    Group g4;
    Data d1;
    Data d2;

    @Before
    public void setUp(){
        g1 = new Group(100, 1, 2, 3);
        g2 = new Group(122, 1, 4, 0, 5, 2, 5, 1034, -32);
        g3 = new Group(592, -1, 2, 5);
        g4 = new Group(229, -1, 10, -2, -3878, 0);

        d1 = new Data("Test 1", new Group(1, 1, 2, 3), new Group(2, -2, -4, -6), new Group(5, 1));
        d2 = new Data("Test 2", g1, g2, g3, g4);
    }

    @Test
    public void testGetAll(){
        assertEquals(new ArrayList<>(
                Arrays.asList(1, 2, 3, -2, -4, -6, 1)),
                DataDemo.getAll(d1));
        assertEquals(new ArrayList<>(
                Arrays.asList(1, 2, 3, 1, 4, 0, 5, 2, 5, 1034, -32, -1, 2, 5, -1, 10, -2, -3878, 0)),
                DataDemo.getAll(d2));

        assertEquals(new ArrayList<>(), DataDemo.getAll(new Data()));

        assertEquals(new ArrayList<>(
                        Arrays.asList(1, 2, 3, 1)),
                DataDemo.getAll(new Data("Test 3", new Group(1, 1, 2, 3), new Group(2), new Group(5, 1))));

        assertEquals(new ArrayList<>(
                        Arrays.asList(1, 2, 3, 1)),
                DataDemo.getAll(new Data("Test 4", new Group(1, 1, 2, 3), new Group(2), new Group(3), new Group(5, 1))));

        assertEquals(new ArrayList<>(
                        Arrays.asList(1, 2, 3, 1)),
                DataDemo.getAll(new Data("Test 4", new Group(1, 1, 2, 3), new Group(2), new Group(5, 1), new Group(3))));
        assertEquals(new ArrayList<>(), DataDemo.getAll(new Data("", new Group(), new Group(), new Group())));
        assertEquals(new ArrayList<>(), DataDemo.getAll(new Data("", new Group())));
        assertEquals(new ArrayList<>(List.of(1)), DataDemo.getAll(new Data("", new Group(), new Group(), new Group(),new Group(), new Group(), new Group(1), new Group(1, 1), new Group(), new Group())));
        assertEquals(new ArrayList<>(List.of(1)), DataDemo.getAll(new Data("", new Group(), new Group(1, 1), new Group())));
    }

}