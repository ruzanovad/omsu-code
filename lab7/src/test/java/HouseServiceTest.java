import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HouseServiceTest {

    @Test
    public void serializeAndDeserialize() throws FileNotFoundException {
        List<Flat> flatList = new ArrayList<>();
        List<Person> people1 = new ArrayList<>();
        people1.add(new Person("Petrov", "Petr", "Petrovich", "13.06.1998"));
        people1.add(new Person("Petrova", "Anna", "Petrovna", "13.01.2022"));

        List<Person> people2 = new ArrayList<>();
        people2.add(new Person("Vernyaeva", "Vera", "Alexandrovna", "11.06.2003"));

        flatList.add(new Flat(1, 100.2, people1));
        flatList.add(new Flat(12, 124.23, people2));
        House house = new House("124asfajkhd3", "Omsk", new Person("Petrov", "Petr", "Petrovich", "13.06.1998"), flatList);
        try (OutputStream os = new FileOutputStream("house.txt")) {
            if (!HouseService.serialize(house, os))
                Assert.fail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStream is = new FileInputStream("house.txt")) {
            House ret = HouseService.deserialize(is);
            assertEquals(house, ret);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void writeToCSV() {
        List<Flat> flatList = new ArrayList<>();
        List<Person> people1 = new ArrayList<>();
        people1.add(new Person("Petrov", "Petr", "Petrovich", "13.06.1998"));
        people1.add(new Person("Petrova", "Anna", "Petrovna", "13.01.2022"));

        List<Person> people2 = new ArrayList<>();
        people2.add(new Person("Vernyaeva", "Vera", "Alexandrovna", "11.06.2003"));

        flatList.add(new Flat(1, 100.2, people1));
        flatList.add(new Flat(12, 124.23, people2));
        House house = new House("124asfajkhd3", "Omsk", new Person("Petrov", "Petr", "Petrovich", "13.06.1998"), flatList);
        assertTrue(HouseService.writeToCSV(house));
    }
    @Test
    public void jackson() throws JsonProcessingException {
        List<Flat> flatList = new ArrayList<>();
        List<Person> people1 = new ArrayList<>();
        people1.add(new Person("Petrov", "Petr", "Petrovich", "13.06.1998"));
        people1.add(new Person("Petrova", "Anna", "Petrovna", "13.01.2022"));

        List<Person> people2 = new ArrayList<>();
        people2.add(new Person("Vernyaeva", "Vera", "Alexandrovna", "11.06.2003"));

        flatList.add(new Flat(1, 100.2, people1));
        flatList.add(new Flat(12, 124.23, people2));
        House house = new House("124asfajkhd3", "Omsk", new Person("Petrov", "Petr", "Petrovich", "13.06.1998"), flatList);
        String json = HouseService.jacksonSerialize(house);
        assertEquals(house, HouseService.jacksonDeserialize(json));
    }

    @Test
    public void compareJson() throws JsonProcessingException {
        String j = """
                {\s
                    "employee":
                    {
                        "id": "1212",
                        "fullName":"John Miles",
                        "age": 34,
                        "contact":
                        {
                            "email": "john@xyz.com",
                            "phone": "9999999999"
                        }
                    }
                }""";
        String g = """
                {
                    "employee":
                    {
                        "id": "1212",
                        "age": 34,
                        "fullName": "John Miles",
                        "contact":
                        {
                            "email": "john@xyz.com",
                            "phone": "9999999999"
                        }
                    }
                }""";
        String t = """
                {
                    "employee":
                    {
                        "id": "1212",
                        "fullName": "John Miles",
                        "age": 34,
                        "skills": ["Java", "C++", "Python"]
                    }
                }""";
        String m = """
                {
                    "employee":
                    {
                        "id": "1212",
                        "age": 34,
                        "fullName": "John Miles",
                        "skills": ["Java", "C++", "Python"]\s
                    }\s
                }""";
        assertTrue(HouseService.compareJson(j, g));
        assertTrue(HouseService.compareJson(t, m));
        assertFalse(HouseService.compareJson(m, g));
    }
}