import org.junit.Test;

import static org.junit.Assert.*;

public class LevenshteinFilterTest {

    @Test
    public void apply() {
        // редакторское расстояние меньше 10
        LevenshteinFilter filter1 = new LevenshteinFilter("Default");
        LevenshteinFilter filter2 = new LevenshteinFilter("Draw Draw");
        LevenshteinFilter filter3 = new LevenshteinFilter("WORLD");

        assertTrue(filter1.apply("Default"));
        assertTrue(filter1.apply("deFaulT"));
        assertFalse(filter1.apply("     Default     "));

        assertTrue(filter2.apply(""));
        assertFalse(filter2.apply("ffffffffffffffffff"));
        assertTrue(filter2.apply("Dw"));

        assertTrue(filter3.apply("love"));
        assertTrue(filter3.apply("loveworld"));
    }
}