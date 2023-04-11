import org.junit.Test;

import static org.junit.Assert.*;

public class EndStringFilterTest {

    @Test
    public void apply() {
        EndStringFilter filter1 = new EndStringFilter("Abra");
        EndStringFilter filter2 = new EndStringFilter("Chips");
        EndStringFilter filter3 = new EndStringFilter("I");

        assertTrue(filter1.apply("AbraAbra"));
        assertFalse(filter1.apply("abraabra"));
        assertFalse(filter1.apply("IDrawable is our custom interface"));

        assertTrue(filter2.apply("Please, buy some Chips"));

        assertFalse(filter3.apply("IDrawable is our custom interface"));
        assertFalse(filter3.apply("I want to order some tortillas"));
        assertTrue(filter3.apply("IIIIIIIIIII IIIIIIIII"));

        assertFalse(filter1.apply(""));
        assertFalse(filter2.apply(""));
        assertFalse(filter3.apply(""));
    }
}