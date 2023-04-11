import java.sql.Ref;
import java.util.*;

import static org.junit.Assert.*;

public class ServiceTest {

    Reference ref1;
    Reference ref2;

    @org.junit.Before
    public void setUp() throws Exception {
        ref1 = new Reference();

        ref1.setName("Петров Петр Петрович");
        ref1.setFaculty("ФЦТК");
        ref1.setUniversity("ОмГУ");
        ref1.setSpecializations(List.of("Прикладная математика и информатика"));

        Row row1 = new Row();
        row1.setSubject("Математический анализ");
        row1.setHours(32);
        row1.setMark(Mark.A);

        Row row2 = new Row();
        row2.setSubject("Комплексный анализ");
        row2.setHours(16);
        row2.setMark(Mark.PASSED);

        Row row3 = new Row();
        row3.setSubject("Функциональный анализ");
        row3.setHours(16);
        row3.setMark(Mark.D);

        Row row4 = new Row();
        row4.setSubject("БЖД");
        row4.setHours(16);
        row4.setMark(Mark.NOT_PASSED);

        Row row5 = new Row();
        row5.setSubject("Алгебра");
        row5.setHours(64);
        row5.setMark(Mark.A);

        List<Row> rows = new ArrayList<>(List.of(row1, row2, row3, row4, row5));

        ref1.setRows(rows);
        ref2 = new Reference();
        ref2.setRows(rows);

        ref2.setName("Петрова Анна Петровна");
        ref2.setFaculty("ФЦТК");
        ref2.setUniversity("ОмГУ");
        ref2.setSpecializations(List.of("Прикладная математика"));

        Row rw1 = new Row();
        rw1.setSubject("Математический анализ");
        rw1.setHours(32);
        rw1.setMark(Mark.B);

        Row rw2 = new Row();
        rw2.setSubject("Машинное обучение");
        rw2.setHours(16);
        rw2.setMark(Mark.PASSED);

        Row rw3 = new Row();
        rw3.setSubject("Функциональный анализ");
        rw3.setHours(16);
        rw3.setMark(Mark.C);

        Row rw4 = new Row();
        rw4.setSubject("БЖД");
        rw4.setHours(16);
        rw4.setMark(Mark.NOT_PASSED);

        Row rw5 = new Row();
        rw5.setSubject("Случайные процессы");
        rw5.setHours(16);
        rw5.setMark(Mark.A);

        List<Row> rws = new ArrayList<>(List.of(rw1, rw2, rw3, rw4, rw5));

        ref2.setRows(rws);
    }

    @org.junit.Test
    public void getSubjects() {
        List<String> s = new ArrayList<>();
        List<String> t = new ArrayList<>();
        Collections.addAll(s, "Математический анализ","Комплексный анализ", "Функциональный анализ", "БЖД", "Алгебра");

        Collections.addAll(t, "Математический анализ","Машинное обучение","Функциональный анализ", "БЖД", "Случайные процессы");
        assertEquals(s, Service.getSubjects(ref1));
        assertEquals(t, Service.getSubjects(ref2));
    }

    @org.junit.Test
    public void getAllHours() {
        assertEquals(144, Service.getAllHours(ref1));
        assertEquals(96, Service.getAllHours(ref2));
    }

    @org.junit.Test
    public void getAverageMark() {
        assertEquals(5.0, Service.getAverageMark(ref1), 1e-9);
        assertEquals(4.0, Service.getAverageMark(ref2), 1e-9);

        {
            Reference ref = new Reference();

            ref.setName("Петров Петр Петрович");
            ref.setFaculty("ФЦТК");
            ref.setUniversity("ОмГУ");
            ref.setSpecializations(List.of("Прикладная математика и информатика"));

            Row row1 = new Row();
            row1.setSubject("Математический анализ");
            row1.setHours(32);
            row1.setMark(Mark.D);

            Row row2 = new Row();
            row2.setSubject("Комплексный анализ");
            row2.setHours(16);
            row2.setMark(Mark.PASSED);

            Row row3 = new Row();
            row3.setSubject("Функциональный анализ");
            row3.setHours(16);
            row3.setMark(Mark.D);

            Row row4 = new Row();
            row4.setSubject("БЖД");
            row4.setHours(16);
            row4.setMark(Mark.NOT_PASSED);

            Row row5 = new Row();
            row5.setSubject("Алгебра");
            row5.setHours(64);
            row5.setMark(Mark.PASSED);

            List<Row> rows = new ArrayList<>(List.of(row1, row2, row3, row4, row5));
            ref.setRows(rows);
            assertNull(Service.getAverageMark(ref));
        }

    }

    @org.junit.Test
    public void getSubjectsWithMarks() {
        Map<String, String> sub = new HashMap<>();
        sub.put("Математический анализ", "отлично");
        sub.put("Комплексный анализ", "зачтено");
        sub.put("Функциональный анализ", "неудовлетворительно");
        sub.put("БЖД", "не зачтено");
        sub.put("Алгебра","отлично");
        assertEquals(sub, Service.getSubjectsWithMarks(ref1));

        Map<String, String> sub1 = new HashMap<>();
        sub1.put("Математический анализ", "хорошо");
        sub1.put("Машинное обучение", "зачтено");
        sub1.put("Функциональный анализ", "удовлетворительно");
        sub1.put("БЖД","не зачтено");
        sub1.put("Случайные процессы", "отлично");
        assertEquals(sub1, Service.getSubjectsWithMarks(ref2));
    }

    @org.junit.Test
    public void getMarksWithSubjects() {
        Map<String, List<String>> sub1 = new HashMap<>();
        sub1.put("отлично", List.of("Математический анализ", "Алгебра"));
        sub1.put("зачтено", List.of("Комплексный анализ"));
        sub1.put("неудовлетворительно", List.of("Функциональный анализ"));
        sub1.put("не зачтено", Collections.singletonList("БЖД"));

        assertEquals(sub1, Service.getMarksWithSubjects(ref1));

        Map<String, List<String>> sub2 = new HashMap<>();
        sub2.put("отлично", List.of("Случайные процессы"));
        sub2.put("зачтено", List.of("Машинное обучение"));
        sub2.put("удовлетворительно", List.of("Функциональный анализ"));
        sub2.put("не зачтено", Collections.singletonList("БЖД"));
        sub2.put("хорошо", Collections.singletonList("Математический анализ"));

        assertEquals(sub2, Service.getMarksWithSubjects(ref2));
    }
}