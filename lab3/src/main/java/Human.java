import java.util.Objects;

public class Human {
    private Date birthDate;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Human(Date birthDate, String name) {
        this.birthDate = birthDate;
        this.name = name;
    }

    public Human(Date birthDate) {
        this.birthDate = birthDate;
        this.name = "Testov Test Testovich";
    }

    @Override
    public String toString() {
        return name+", birth date: "+birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(birthDate, human.birthDate) && Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, name);
    }
}
