import java.util.Objects;

public class Human {
    private String surname;
    private String name;
    private String patronym;
    private int age;

    public Human(String surname, String name, String patronym, int age) {
        this.surname = surname;
        this.name = name;
        this.patronym = patronym;
        this.age = age;
    }

    public Human(Human other){
        this(other.surname, other.name, other.patronym, other.age);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(surname, human.surname) && Objects.equals(name, human.name) && Objects.equals(patronym, human.patronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronym, age);
    }
}
