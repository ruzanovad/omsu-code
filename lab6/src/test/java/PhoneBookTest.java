import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PhoneBookTest {
    PhoneBook p1;
    PhoneBook p2;
    PhoneBook p3;

    @Before
    public void setUp() {
        p1 = new PhoneBook();

        p1.addNumber(new Human("Крылов", "Василий", "Андреевич", 43), "74956978948");
        p1.addNumber(new Human("Крылов", "Василий", "Андреевич", 43), "79653673994");

        p1.addNumber(new Human("Крылова", "Екатерина", "Андреевна", 44), "74956695947");
        p1.addNumber(new Human("Ким", "Анна", "Васильевна", 18), "74959249185");
        p1.addNumber(new Human("Штерн", "Валерий", "Борисович", 34), "74953381447");
        p1.addNumber(new Human("Шукшин", "Евгений", "Евгеньевич", 9), "74951087805");

        p2 = new PhoneBook();
        p2.addNumber(new Human("Васильев", "Василий", "Александрович", 5), "79650138907");

        p2.addNumber(new Human("Алексеев", "Алексей", "Алексеевич", 15), "79651895155");
        p2.addNumber(new Human("Алексеев", "Алексей", "Алексеевич", 15), "79655372021");
        p2.addNumber(new Human("Алексеев", "Алексей", "Алексеевич", 15), "79657517491");

        p2.addNumber(new Human("Соловьев", "Михаил", "Андреевич", 75), "79654445896");

        p3 = new PhoneBook();
        p3.addNumber(new Human("Маркс", "Карл", "", 61), "79650644022");
        p3.addNumber(new Human("Гончарова", "Екатерина", "Николаевна", 30), "79654693665");
        p3.addNumber(new Human("Липшиц", "Рудольф", "", 60), "79654446998");
        p3.addNumber(new Human("Пушкин", "Лев", "Сергеевич", 30), "79654292261");

    }

    @Test
    public void deleteNumber() {
        assertTrue(p1.deleteNumber(new Human("Крылов", "Василий", "Андреевич", 43), "79653673994"));
        assertTrue(p1.deleteNumber(new Human("Крылов", "Василий", "Андреевич", 43), "74956978948"));
        assertFalse(p1.deleteNumber(new Human("Крылов", "Василий", "Андреевич", 43), "79655372021"));
    }

    @Test
    public void getListOfNumbers() {
        List<String> stringList = new ArrayList<>();
        stringList.add("79651895155");
        stringList.add("79655372021");
        stringList.add("79657517491");

        assertEquals(stringList, p2.getListOfNumbers(new Human("Алексеев", "Алексей", "Алексеевич", 15)));
        assertThrows(NoSuchElementException.class, ()-> p3.getListOfNumbers(new Human("Соловьев", "Михаил", "Андреевич", 75)));
    }

    @Test
    public void findByNumber() {
        assertThrows(NoSuchElementException.class, ()->p1.findByNumber("79654292261"));
        assertEquals(new Human("Ким", "Анна", "Васильевна", 18) ,p1.findByNumber("74959249185"));
        assertEquals(new Human("Крылов", "Василий", "Андреевич", 43), p1.findByNumber("74956978948"));
        assertEquals(new Human("Крылов", "Василий", "Андреевич", 43), p1.findByNumber("79653673994"));
    }

    @Test
    public void getAllBeginsWith() {
        PhoneBook p = new PhoneBook();
        p.addNumber(new Human("Крылов", "Василий", "Андреевич", 43), "74956978948");
        p.addNumber(new Human("Крылов", "Василий", "Андреевич", 43), "79653673994");
        p.addNumber(new Human("Крылова", "Екатерина", "Андреевна", 44), "74956695947");
        p.addNumber(new Human("Ким", "Анна", "Васильевна", 18), "74959249185");

        Map<Human, List<String>> h = new HashMap<>();
        h.put(new Human("Крылов", "Василий", "Андреевич", 43),
                p.getListOfNumbers(new Human("Крылов", "Василий", "Андреевич", 43)));
        h.put(new Human("Крылова", "Екатерина", "Андреевна", 44),
                p.getListOfNumbers(new Human("Крылова", "Екатерина", "Андреевна", 44)));
        h.put(new Human("Ким", "Анна", "Васильевна", 18),
                p.getListOfNumbers(new Human("Ким", "Анна", "Васильевна", 18)));

        assertEquals(h, p1.getAllBeginsWith("К"));
        h.remove(new Human("Ким", "Анна", "Васильевна", 18));
        assertEquals(h, p1.getAllBeginsWith("Кры"));
        assertEquals(new HashMap<>(), p3.getAllBeginsWith("Бббб"));
    }

}