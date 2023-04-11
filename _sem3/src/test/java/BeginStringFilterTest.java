import org.junit.Test;

import static org.junit.Assert.*;

public class BeginStringFilterTest {

    @Test
    public void apply() {
        BeginStringFilter filter1 = new BeginStringFilter("Abra");
        BeginStringFilter filter2 = new BeginStringFilter("Chips");
        BeginStringFilter filter3 = new BeginStringFilter("I");

        assertTrue(filter1.apply("Abraabra"));
        assertFalse(filter1.apply("abraabra"));
        assertFalse(filter1.apply("IDrawable is our custom interface"));

        assertTrue(filter2.apply("Chips are my favourite snack"));

        assertTrue(filter3.apply("IDrawable is our custom interface"));
        assertTrue(filter3.apply("I want to order some tortillas"));

        assertFalse(filter1.apply(""));
        assertFalse(filter2.apply(""));
        assertFalse(filter3.apply(""));
    }
}