package as.nl.si.examcon.model.studentResponse;

import as.nl.si.examcon.model.studentResponse.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Embedded {
    private Student[] students;

    @JsonProperty("students")
    public Student[] getStudents() {
        return students;
    }

    @JsonProperty("students")
    public void setStudents(Student[] value) {
        this.students = value;
    }
}
