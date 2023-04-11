import org.junit.Test;

import static org.junit.Assert.*;

public class RationalFunctionTest {

    @Test
    public void getValue() {
        RationalFunction f = new RationalFunction(-100, 100, 1, 2, 1, 2);
        assertEquals(1, f.getValue(1), 1e-9);
        assertEquals(1, f.getValue(2), 1e-9);
        assertEquals(1, f.getValue(0), 1e-9);

        RationalFunction f1 = new RationalFunction(-10, 10, 1, -1, 1, 1);

        assertEquals(1./3, f1.getValue(2), 1e-9);
        assertEquals(0, f1.getValue(1), 1e-9);
    }
}