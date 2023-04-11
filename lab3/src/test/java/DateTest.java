import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {
    @Test
    public void Date() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(1, 13, 2022);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(5, "Random string", 2022);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(29, 2, 2021);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(30, 2, 2000);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(31, "June", 1927);
        });

        Date d = new Date(30, 9, 2010);
        assertEquals(30, d.getDay());
        assertEquals(2010, d.getYear());
        assertEquals("September", d.getMonth());
    }

    @org.junit.Test
    public void getDay() {
        Date d1 = new Date(1, 1, 2003);
        Date d2 = new Date(16, 6, 2003);
        assertEquals(1, d1.getDay());
        assertEquals(16, d2.getDay());
    }

    @org.junit.Test
    public void setDay() {

        Date d1 = new Date(1, 2, 2003);
        Date d2 = new Date(16, 6, 2003);
        d1.setDay(15);
        assertEquals(15, d1.getDay());
        assertThrows(IllegalArgumentException.class, () -> {
            d2.setDay(31);
        });
    }

    @org.junit.Test
    public void getMonth() {
        Date d1 = new Date(1, "January", 2003);
        Date d2 = new Date(16, 6, 2003);
        assertEquals("January", d1.getMonth());
        assertEquals("June", d2.getMonth());
    }

    @org.junit.Test
    public void setMonth() {
        Date d1 = new Date(1, 1, 2003);
        Date d2 = new Date(16, 6, 2003);
        Date d3 = new Date(31, 7, 1569);
        d1.setMonth(12);
        d2.setMonth(3);
        assertEquals("December", d1.getMonth());
        assertEquals("March", d2.getMonth());
        assertThrows(IllegalArgumentException.class, () -> {
            d3.setMonth("February");
        });
    }

    @org.junit.Test
    public void getYear() {
        Date d1 = new Date(1, "January", 2003);
        Date d2 = new Date(16, 6, 1034);
        assertEquals(2003, d1.getYear());
        assertEquals(1034, d2.getYear());
    }

    @org.junit.Test
    public void setYear() {
        Date d1 = new Date(1, "January", 2003);
        Date d2 = new Date(29, 2, 2000);
        d1.setYear(2000);
        assertEquals(2000, d1.getYear());
        assertThrows(IllegalArgumentException.class, () -> {
            d2.setYear(2001);
        });
    }

    @org.junit.Test
    public void testToString() {
        Date d1 = new Date(1, "January", 2003);
        Date d2 = new Date(31, 7, 1569);
        assertEquals("1 January, 2003", d1.toString());
        assertEquals("31 July, 1569", d2.toString());
    }

    @Test
    public void isLeapYear() {
        assertTrue((new Date(1, 10, 2000)).isLeapYear());
        assertTrue((new Date(1, 10, 2004)).isLeapYear());
        assertFalse((new Date(1, 10, 1000)).isLeapYear());
    }

    @Test
    public void afterTest() {
        Date d1 = new Date(1, 1, 2003);
        Date d2 = new Date(16, 6, 2003);
        Date d3 = new Date(10, 12, 2003);
        Date d4 = new Date(5, 10, 1984);
        Date d5 = new Date(24, 3, 1333);
        Date d6 = new Date(19, 5, 2022);
        Date d7 = new Date(20, 5, 2022);
        assertTrue(d2.after(d1));
        assertFalse(d2.after(d2));
        assertTrue(d3.after(d5));
        assertTrue(d6.after(d4));
        assertTrue(d7.after(d6));
    }
}