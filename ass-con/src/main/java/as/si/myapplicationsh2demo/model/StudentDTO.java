package as.si.myapplicationsh2demo.model;

import as.si.myapplicationsh2demo.model.studentResponse.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentDTO {
    private String sname;
    private String smail;
    private String assignment;

    public StudentDTO(Assignment assignment, Student student) {
        this.sname = student.getSname();
        this.smail = student.getSmail();
        this.assignment = assignment.getAname();
    }
    public StudentDTO(Student student) {
        this.sname = student.getSname();
        this.smail = student.getSmail();
    }

    @JsonProperty("name")
    public String getSname() {
        return sname;
    }

    @JsonProperty("name")
    public void setSname(String value) {
        this.sname = value;
    }

    @JsonProperty("mail")
    public String getSmail() {
        return smail;
    }

    @JsonProperty("mail")
    public void setSmail(String value) {
        this.smail = value;
    }

    @JsonProperty("assignment")
    public String getAssignment() {
        return assignment;
    }

    @JsonProperty("assignment")
    public void setAssignment(String value) {
        this.assignment = value;
    }
}
