import org.junit.Test;

import static org.junit.Assert.*;

public class DefiniteIntegralTest {

    @Test
    public void getValue() {
        SineFunction f = new SineFunction(-Math.PI, Math.PI, 1, 0.5);
        LinearFunction l  = new LinearFunction(-100, 10, 1, 0);
        ExponentFunction e = new ExponentFunction(-1, 1, 1, 1);
        RationalFunction r = new RationalFunction(-0.5, 0.5, 1, -1, 1, 1);

        assertThrows(IllegalArgumentException.class, ()->{new DefiniteIntegral<>(-5, 5).getValue(f);});

        DefiniteIntegral<SineFunction> sineFunctionDefiniteIntegral = new DefiniteIntegral<>(
                -Math.PI * .5, Math.PI *.5);
        DefiniteIntegral<LinearFunction> linearFunctionDefiniteIntegral = new DefiniteIntegral<>(-100, 10);
        DefiniteIntegral<ExponentFunction> exponentFunctionDefiniteIntegral = new DefiniteIntegral<>(-1, 0);
        DefiniteIntegral<RationalFunction> rationalFunctionDefiniteIntegral = new DefiniteIntegral<>(-0.5, 0.5);

        assertEquals(0, sineFunctionDefiniteIntegral.getValue(f), 1e-9);
        assertEquals(-4950, linearFunctionDefiniteIntegral.getValue(l), 1e-9);
        assertEquals(2 - Math.exp(-1), exponentFunctionDefiniteIntegral.getValue(e), 1e-9);
        assertEquals(1 - 2*Math.log(1.5/0.5), rationalFunctionDefiniteIntegral.getValue(r), 1e-9);
    }
}