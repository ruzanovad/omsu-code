public class Student extends Human{
    private String faculty;
    public Student(String surname, String name, String patronym, int age, String faculty) {
        super(surname, name, patronym, age);
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
