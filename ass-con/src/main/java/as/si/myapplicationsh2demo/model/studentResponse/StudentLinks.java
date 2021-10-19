package as.si.myapplicationsh2demo.model.studentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentLinks {
    private Profile self;
    private Profile student;

    @JsonProperty("self")
    public Profile getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Profile value) {
        this.self = value;
    }

    @JsonProperty("student")
    public Profile getStudent() {
        return student;
    }

    @JsonProperty("student")
    public void setStudent(Profile value) {
        this.student = value;
    }
}
