import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTest {

    @org.junit.Test
    public void binStream() throws FileNotFoundException {
        int[] x = {1, 2, 3, 4, 5, 6};
        try (OutputStream f = new FileOutputStream("bin.txt")) {
            Task.writeToBinStream(f, x);
            int[] y = new int[6];
            Task.readFromBinStream(new FileInputStream("bin.txt"), y);
            assertArrayEquals(x, y);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @org.junit.Test
    public void charStream() {
        int[] x = {1, 2, 3, 4, 5, 6};
        try (Writer f = new FileWriter("char.txt")) {
            Task.writeToCharStream(f, x);
            int[] y = new int[6];
            Task.readFromCharStream(new FileReader("char.txt"), y);
            assertArrayEquals(x, y);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @org.junit.Test
    public void readRandom() throws FileNotFoundException {
        try (RandomAccessFile f = new RandomAccessFile("raf.txt", "rw")) {
            for (int i: new int[]{1, 2, 3, -1, -2, -3, -4})
                f.writeInt(i);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        int[] t = {3, -1, -2, -3, -4};
        int[] r = new int[5];
        Task.readRandom("raf.txt", 2, r);
        assertArrayEquals(t, r);

    }

    @org.junit.Test
    public void getAllWithGivenExtension() {
        List<String> f = new ArrayList<>();
        f.add("g.html");
        f.add("t.html");
        f.sort(String::compareTo);
        List<String> ret = Task.getAllWithGivenExtension("test", "html");
        assertNotNull(ret);
        assertEquals(f, ret);

        List<String> g = new ArrayList<>();
        g.add("djfso.js");
        g.add("s.js");
        g.sort(String::compareTo);
        List<String> ret2 = Task.getAllWithGivenExtension("test", "js");
        assertNotNull(ret2);
        assertEquals(g, ret2);

        List<String> e = new ArrayList<>();
        e.add("empty");

        List<String> ret3 = Task.getAllWithGivenExtension("test", "");

        assertNotNull(ret3);
        assertEquals(e, ret3);
    }

    @org.junit.Test
    public void getAllFromRegularRecursively() {
        List<String> expected = new ArrayList<>();
        expected.add("C:\\Users\\light\\omsu\\lab7\\test\\Geometry[12]");
        expected.add("C:\\Users\\light\\omsu\\lab7\\test\\History[1]");
        expected.add("C:\\Users\\light\\omsu\\lab7\\test\\ff\\Algebra\\Algebra[101]");
        expected.add("C:\\Users\\light\\omsu\\lab7\\test\\ff\\Geometry[11]");

        expected.sort(String::compareTo);

        List<String> ret = Task.getAllFromRegularRecursively("test", "(Algebra|Geometry|History)\\[\\d*\\]");

        assertNotNull(ret);
        ret.sort(String::compareTo);

        assertEquals(expected, ret);

        List<String> expected2 = new ArrayList<>();
        expected2.add("C:\\Users\\light\\omsu\\lab7\\test\\ff\\56466a.js");
        expected2.add("C:\\Users\\light\\omsu\\lab7\\test\\djfso.js");
        expected2.add("C:\\Users\\light\\omsu\\lab7\\test\\s.js");
        expected2.add("C:\\Users\\light\\omsu\\lab7\\test\\t.html");
        expected2.add("C:\\Users\\light\\omsu\\lab7\\test\\g.html");

        expected2.sort(String::compareTo);

        List<String> ret2 = Task.getAllFromRegularRecursively("test", "[a-zA-Z0-9_]*\\.(js|html)");

        assertNotNull(ret2);
        ret2.sort(String::compareTo);

        assertEquals(expected2, ret2);
    }
}