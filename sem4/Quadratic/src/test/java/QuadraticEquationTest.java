import java.util.Arrays;

import static org.junit.Assert.*;

public class QuadraticEquationTest {

    @org.junit.Test
    public void solve() {
        QuadraticEquation q1 = new QuadraticEquation(1, 2, 1);
        QuadraticEquation q2 = new QuadraticEquation(1, 2, 0);
        QuadraticEquation q3 = new QuadraticEquation(1, 0, 4);


        QuadraticEquation q4 = new QuadraticEquation(0, 2, 1);
        QuadraticEquation q5 = new QuadraticEquation(0, 0, 1);
        QuadraticEquation q6 = new QuadraticEquation(0, 0, 0);

        assertArrayEquals(new double[]{-1}, q1.solve(), 1e-9);
        assertArrayEquals(new double[]{-2, 0}, q2.solve(), 1e-9);
        assertArrayEquals(new double[]{Double.NEGATIVE_INFINITY}, q3.solve(), 1e-9);
        assertArrayEquals(new double[]{-.5}, q4.solve(), 1e-9);
        assertArrayEquals(new double[]{}, q5.solve(), 1e-9);
        assertArrayEquals(new double[]{Double.POSITIVE_INFINITY}, q6.solve(), 1e-9);
    }

}