import java.util.*;

import static org.junit.Assert.*;
public class ReflectionDemoTest {

    @org.junit.Test
    public void getHumans() {
        List<Object> objectList = new ArrayList<>();
        objectList.add(new Human("", "", "", 1));
        objectList.add(3);
        objectList.add(true);
        objectList.add(new TreeSet<>());
        objectList.add(new Student("", "", "", 1, ""));
        objectList.add("");
        objectList.add(new ArrayList<Student>(List.of(new Student("", "", "", 1, ""))));

        assertEquals(2, ReflectionDemo.getHumans(objectList));
    }

    @org.junit.Test
    public void getMethods() {
        String s = """
                getName
                equals
                hashCode
                setName
                setPatronym
                getSurname
                getPatronym
                setSurname
                getAge
                setAge
                wait
                wait
                wait
                toString
                getClass
                notify
                notifyAll
                """;
        String[] ss = s.split("\n");
        List<String> list= new ArrayList<>(List.of(ss));
        list.sort(String::compareTo);
        List<String> actual = new ArrayList<>(ReflectionDemo.getMethods(new Human("", "", "", 1)));
        actual.sort(String::compareTo);
        assertEquals(list, actual);

        list.add("getFaculty");
        list.add("setFaculty");
        list.sort(String::compareTo);
        List<String> actual2 =new ArrayList<>( ReflectionDemo.getMethods(new Student("", "", "", 1, "")));
        actual2.sort(String::compareTo);

        assertEquals(list, actual2);
    }

    @org.junit.Test
    public void getAllSuper() {
        {
            List<String> studentClasses = new ArrayList<>();
            studentClasses.add("Human");
            studentClasses.add("java.lang.Object");
            assertEquals(studentClasses, ReflectionDemo.getAllSuper(new Student("", "", "", 1, "")));
        }


        {
            List<String> queue = new ArrayList<>();
            queue.add("java.util.AbstractQueue");
            queue.add("java.util.AbstractCollection");
            queue.add("java.lang.Object");
            assertEquals(queue,  ReflectionDemo.getAllSuper(new PriorityQueue<>()));
        }
    }

    public static class A implements Executable {
        @Override
        public void execute() {

        }
    }

    public static class B extends A {

    }

    public static class C extends B {
    }

    @org.junit.Test
    public void countExecutable() {
        List<Object> list = new ArrayList<>();
        list.add(2);
        list.add(false);
        list.add(.2);
        list.add(new Printer());
        list.add(new Writer());
        list.add(new PriorityQueue<>());
        list.add(new A());
        list.add(new B());
        list.add(new C());

        assertEquals(5, ReflectionDemo.countExecutable(list));
    }

    @org.junit.Test
    public void getGettersAndSetters() {
        List<String> hum = new ArrayList<>(List.of("getName", "setName", "setSurname", "setAge", "getSurname", "getPatronym", "setPatronym", "getAge", "getClass"));

        List<String> humActual = new ArrayList<>(ReflectionDemo.getGettersAndSetters(new Human("", "", "", 1)));

        hum.sort(String::compareTo);
        humActual.sort(String::compareTo);

        assertEquals(hum, humActual);

        hum.add("getFaculty");
        hum.add("setFaculty");

        List<String> actual = new ArrayList<>(ReflectionDemo.getGettersAndSetters(new Student("", "", "", 1, "")));

        hum.sort(String::compareTo);
        actual.sort(String::compareTo);

        assertEquals(hum, actual);
    }
}