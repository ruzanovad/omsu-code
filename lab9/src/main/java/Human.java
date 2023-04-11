import java.util.Objects;

public class Human {
    private String surname;
    private String name;
    private String patronym;

    private int age;
    private Gender gender;

    public Human(Human other) {
        this.surname = other.surname;
        this.name = other.name;
        this.patronym = other.patronym;
        this.age = other.age;
        this.gender = other.gender;
    }

    public Human(String surname, String name, String patronym, int age, Gender gender) {
        this.surname = surname;
        this.name = name;
        this.patronym = patronym;
        this.age = age;
        this.gender = gender;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(surname, human.surname) && Objects.equals(name, human.name) && Objects.equals(patronym, human.patronym) && gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronym, age, gender);
    }
}
