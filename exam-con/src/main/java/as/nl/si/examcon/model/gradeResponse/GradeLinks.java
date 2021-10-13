package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeLinks {
    private First self;
    private First grade;

    @JsonProperty("self")
    public First getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(First value) {
        this.self = value;
    }

    @JsonProperty("grade")
    public First getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(First value) {
        this.grade = value;
    }
}
