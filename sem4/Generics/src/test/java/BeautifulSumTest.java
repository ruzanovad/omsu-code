import org.junit.Test;

import javax.sound.sampled.Line;

import static org.junit.Assert.*;

public class BeautifulSumTest {

    @Test
    public void getValue() {

        BeautifulSum<SineFunction> sineFunctionBeautifulSum = new BeautifulSum<>();

        SineFunction f = new SineFunction(-Math.PI, Math.PI, 1, 0.5);
        assertEquals(0, sineFunctionBeautifulSum.getValue(f), 1e-9);

        BeautifulSum<LinearFunction> linearFunctionBeautifulSum = new BeautifulSum<>();

        LinearFunction l  = new LinearFunction(-100, 10, 1, 0);
        assertEquals(-135, linearFunctionBeautifulSum.getValue(l), 1e-9);

        BeautifulSum<ExponentFunction> exponentFunctionBeautifulSum = new BeautifulSum<>();

        ExponentFunction e = new ExponentFunction(-1, 1, 1, 1);
        assertEquals(Math.exp(1)+Math.exp(-1)+4, exponentFunctionBeautifulSum.getValue(e), 1e-9);

        BeautifulSum<RationalFunction> rationalFunctionBeautifulSum = new BeautifulSum<>();

        RationalFunction r = new RationalFunction(-10, 10, 1, -1, 1, 1);
        assertEquals(-11./(-9) + 9./11 -1, rationalFunctionBeautifulSum.getValue(r), 1e-9);


    }
}