import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reference {
    private String name;
    private String university;
    private String faculty;
    private List<String> specializations;
    private Date start;
    private Date end;
    private List<Row> rows;

    public Reference(){
        name = "";
        university = "";
        faculty = "";
        specializations = new ArrayList<>();
        start = new Date();
        end = new Date();
        rows = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
