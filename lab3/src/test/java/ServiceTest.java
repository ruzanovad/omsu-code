import org.junit.Before;
import org.junit.Test;

import javax.management.InstanceNotFoundException;

import static org.junit.Assert.*;

public class ServiceTest {
    private Human[] a;
    private Human[] b;
    private Human[] c;

    @Before
    public void setUp() {
        a = new Human[]{
                new Human(new Date(29, 2, 2000)),
                new Human(new Date(4, "August", 2003)),
                new Human(new Date(19, 1, 2002)),
                new Human(new Date(28, "July", 2001)),
                new Human(new Date(14, 11, 2005)),
                new Human(new Date(22, "October", 2004)),
                new Human(new Date(19, 10, 2006))};
        b = new Human[]{
                new Human(new Date(28, 2, 2001)),
                new Human(new Date(4, 9, 2007)),
                new Human(new Date(19, 1, 1993)),
                new Human(new Date(27, 7, 2001)),
                new Human(new Date(14, 11, 1980)),
                new Human(new Date(11, "April", 2007)),
                new Human(new Date(20, 10, 1995))};
        c = new Human[]{
                new Human(new Date(4, 11, 2022)),
                new Human(new Date(12, 12, 2010))
        };

    }

    @Test
    public void findAdults() {
        assertArrayEquals(new Human[]{
                new Human(new Date(29, 2, 2000)),
                new Human(new Date(4, "August", 2003)),
                new Human(new Date(19, 1, 2002)),
                new Human(new Date(28, "July", 2001)),
        }, Service.findAdults(a, new Date(22, "October", 2022)));
        assertArrayEquals(new Human[]{
                new Human(new Date(28, 2, 2001)),
                new Human(new Date(19, 1, 1993)),
                new Human(new Date(27, 7, 2001)),
                new Human(new Date(14, 11, 1980)),
                new Human(new Date(20, 10, 1995))},
                Service.findAdults(b, new Date(22, 10, 2022)));

    }

    @Test
    public void getAges() throws InstanceNotFoundException {
//        new Human(new Date(29, 2, 2000)),
//                new Human(new Date(4, "August", 2003)),
//                new Human(new Date(19, 1, 2002)),
//                new Human(new Date(28, "July", 2001)),
//                new Human(new Date(14, 11, 2005)),
//                new Human(new Date(22, "October", 2004)),
//                new Human(new Date(19, 10, 2006))};

//        new Human(new Date(4, 11, 2022)),
//                new Human(new Date(12, 12, 2010))
        assertArrayEquals(new int[]{22, 19, 20, 21, 16, 18, 16}, Service.getAges(a, new Date(4, 11, 2022)));
        assertArrayEquals(new int[]{0, 11}, Service.getAges(c, new Date(4, 11, 2022)));
        assertThrows(IllegalArgumentException.class, () -> {Service.getAges(
                new Human[]{new Human(new Date(5, 11, 2022))}, new Date(4, 11, 2022)
        );});
    }
}