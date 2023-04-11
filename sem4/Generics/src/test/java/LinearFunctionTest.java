import org.junit.Test;

import static org.junit.Assert.*;

public class LinearFunctionTest {

    @Test
    public void getValue() {
        LinearFunction f = new LinearFunction(-10, 10, 1, 0);

        assertEquals(1, f.getValue(1), 1e-9);
        assertEquals(-10, f.getValue(-10), 1e-9);

        LinearFunction f1 = new LinearFunction(-10, 10, -2, 3);

        assertEquals(2, f1.getValue(1./2), 1e-9);
    }
}