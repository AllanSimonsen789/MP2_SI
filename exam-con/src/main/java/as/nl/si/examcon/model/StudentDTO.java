package as.nl.si.examcon.model;

import as.nl.si.examcon.model.gradeResponse.Grade;
import as.nl.si.examcon.model.gradeResponse.GradeLinks;
import as.nl.si.examcon.model.studentResponse.Student;
import as.nl.si.examcon.model.studentResponse.StudentLinks;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentDTO {
    private String sname;
    private String smail;
    private long grade;
    private StudentLinks sLinks;
    private GradeLinks gLinks;

    public StudentDTO(Grade grade, Student student) {
        this.sname = student.getSname();
        this.smail = student.getSmail();
        this.grade = grade.getGrade();
        this.sLinks = student.getLinks();
        this.gLinks = grade.getLinks();
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

    @JsonProperty("_student_links")
    public StudentLinks getSlinks() {
        return sLinks;
    }

    @JsonProperty("_student_links")
    public void setSlinks(StudentLinks value) {
        this.sLinks = value;
    }

    @JsonProperty("grade")
    public long getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(long value) {
        this.grade = value;
    }

    @JsonProperty("_grade_links")
    public GradeLinks getGlinks() {
        return gLinks;
    }

    @JsonProperty("_grade_links")
    public void setGlinks(GradeLinks value) {
        this.gLinks = value;
    }

}
