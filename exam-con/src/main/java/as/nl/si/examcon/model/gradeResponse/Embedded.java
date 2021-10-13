package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Embedded {
    private Grade[] grades;

    @JsonProperty("grades")
    public Grade[] getGrades() {
        return grades;
    }

    @JsonProperty("grades")
    public void setGrades(Grade[] value) {
        this.grades = value;
    }
}
