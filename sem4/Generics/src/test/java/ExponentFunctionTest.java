import org.junit.Test;

import static org.junit.Assert.*;

public class ExponentFunctionTest {

    @Test
    public void getValue() {
        ExponentFunction f = new ExponentFunction(-10, 10, 1, -10);

        assertEquals(-8, f.getValue(Math.log(2)), 1e-9);
        assertEquals(-9.5, f.getValue(Math.log(0.5)), 1e-9);

        assertEquals(-9, f.getValue(0), 1e-9);

    }
}