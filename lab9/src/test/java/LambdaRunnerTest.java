import static org.junit.Assert.*;

public class LambdaRunnerTest {
    Student student;
    Human human;
    Human human1;
    Student student1;
    Student student2;
    Student student3;

    @org.junit.Before
    public void SetUp(){
        student = new Student("Pushkin", "Alexander", "Sergeevich", 33, Gender.MALE,
                "OmSU", "FCTK", "Applied Mathematics");

        human =  new Human("Pushkin", "Alexander", "Sergeevich", 33, Gender.MALE);

        human1 =  new Human("Pushkin", "Alexander", "Sergeevich", 34, Gender.MALE);

        student1 = new Student("Pushkin", "Alexander", "Sergeevich", 33, Gender.MALE,
                "OmSU", "FCTK", "Applied Mathematics");

        student2 = new Student("Tolstoy", "Alexander", "Sergeevich", 33, Gender.MALE,
                "OmSU", "FCTK", "Applied Mathematics");
        student3 = new Student("Pushkin", "Alexander", "Sergeevich", 33, Gender.MALE,
                "OmSU", "FCTK", "Applied Physics");

    }

    @org.junit.Test
    public void oneParam() {
        assertEquals('s', LambdaRunner.oneParam("sdsfsfe", LambdaDemo.getFirstChar).charValue());
        assertNull(LambdaRunner.oneParam("", LambdaDemo.getFirstChar));
        assertNull(LambdaRunner.oneParam(null, LambdaDemo.getFirstChar));
        assertFalse(LambdaRunner.oneParam("Aa aasf ooo fff", LambdaDemo.noSpaces));
        assertEquals(4, LambdaRunner.oneParam("aaaa", LambdaDemo.getLength).intValue());
        assertEquals(5, LambdaRunner.oneParam(
                "Today,I've,passed,the,test", LambdaDemo.getCountOfWords).intValue());
        assertEquals(5, LambdaRunner.oneParam(
                ",,Today  ,,,I've,passed ,the,test,,", LambdaDemo.getCountOfWords).intValue());
        assertEquals(2, LambdaRunner.oneParam(
                " , ", LambdaDemo.getCountOfWords).intValue());
        assertEquals(4, LambdaRunner.oneParam(
                ", ,, , ,  ,", LambdaDemo.getCountOfWords).intValue());
        assertEquals(0, LambdaRunner.oneParam(
                ",", LambdaDemo.getCountOfWords).intValue());
        assertEquals(1, LambdaRunner.oneParam(
                " ", LambdaDemo.getCountOfWords).intValue());
        assertEquals(4, LambdaRunner.oneParam(
                "  ,  ,,   , ", LambdaDemo.getCountOfWords).intValue());
        assertEquals(8, LambdaRunner.oneParam(
                "  , a ,,  ab , asd,  ,, ,,,,   ,s", LambdaDemo.getCountOfWords).intValue());

        assertEquals(33, LambdaRunner.oneParam(student, LambdaDemo.getHumanAge).intValue());

        assertEquals("Pushkin Alexander Sergeevich", LambdaRunner.oneParam(student, LambdaDemo.getSNP));

        assertEquals("Pushkin Alexander Sergeevich", LambdaRunner.oneParam(student, LambdaDemo.getSNP));

        assertEquals(human1, LambdaRunner.oneParam(human, LambdaDemo.makeOlder));
    }

    @org.junit.Test
    public void twoParams() {
        assertTrue(LambdaRunner.twoParams(student, student1, LambdaDemo.areSurnamesEqual));
        assertFalse(LambdaRunner.twoParams(student2, student3, LambdaDemo.areSurnamesEqual));
    }

    @org.junit.Test
    public void uniqueParam() {
        Student stud = new Student("Pushkin", "Alexander", "Sergeevich", 23, Gender.MALE, "OmSU", "FCTK", "Applied Mathematics");
        Student stud1 = new Student("Pushkin", "Alexander", "Sergeevich", 10, Gender.MALE, "OmSU", "FCTK", "Applied Mathematics");
        Student stud2 = new Student("Pushkin", "Alexander", "Sergeevich", 24, Gender.MALE, "OmSU", "FCTK", "Applied Mathematics");

        assertTrue(LambdaRunner.uniqueParam(stud, stud1, stud2,25, LambdaDemo.checkYounger));
        Human hum = LambdaRunner.oneParam(stud2, LambdaDemo.makeOlder);
        assertFalse(LambdaRunner.uniqueParam(stud, stud1, hum,25, LambdaDemo.checkYounger));

    }
}