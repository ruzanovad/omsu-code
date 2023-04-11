import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QuadraticServiceTest {

    @Test
    public void foundLargestRoot() {
        QuadraticEquation q1 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation q2 = new QuadraticEquation(1, 2, 0);
        QuadraticEquation q3 = new QuadraticEquation(1, 0, 4);


        QuadraticEquation q4 = new QuadraticEquation(0, 2, 1);
        QuadraticEquation q5 = new QuadraticEquation(0, 0, 1);
        QuadraticEquation q6 = new QuadraticEquation(0, 0, 0);

        QuadraticService s1 = new QuadraticService(q1);
        QuadraticService s2 = new QuadraticService(q2);
        QuadraticService s3 = new QuadraticService(q3);
        QuadraticService s4 = new QuadraticService(q4);
        QuadraticService s5 = new QuadraticService(q5);
        QuadraticService s6 = new QuadraticService(q6);

        assertEquals(-1, s1.findLargestRoot(), 1e-9);
        assertEquals(0, s2.findLargestRoot(), 1e-9);
        assertThrows(NoSuchElementException.class, s3::findLargestRoot);
        assertEquals(-.5, s4.findLargestRoot(), 1e-9);
        assertThrows(NoSuchElementException.class, s5::findLargestRoot);
        assertEquals(Double.POSITIVE_INFINITY, s6.findLargestRoot(), 1e-9);
    }
}