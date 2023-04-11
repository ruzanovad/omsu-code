import org.junit.Test;

import static org.junit.Assert.*;

public class SineFunctionTest {

    @Test
    public void getValue() {
        SineFunction f =  new SineFunction(-2* Math.PI, 2*Math.PI, 1, 1);
        assertEquals(0, f.getValue(0), 1e-9);
        assertEquals(-1, f.getValue(-Math.PI/2), 1e-9);
        assertEquals(1, f.getValue(Math.PI/2), 1e-9);
        assertEquals(0.5, f.getValue(Math.PI/6), 1e-9);
        SineFunction f1 =  new SineFunction(-2* Math.PI, 2*Math.PI, 2, 2);
        assertEquals(0, f1.getValue(0), 1e-9);
        assertEquals(0, f1.getValue(-Math.PI/2), 1e-9);
        assertEquals(0, f1.getValue(Math.PI/2), 1e-9);
        assertEquals(Math.sqrt(3), f1.getValue(Math.PI/6), 1e-9);
    }
}