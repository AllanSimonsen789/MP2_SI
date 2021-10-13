package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeReponseLinks {
    private First first;
    private First self;
    private First next;
    private First last;
    private First profile;

    @JsonProperty("first")
    public First getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(First value) {
        this.first = value;
    }

    @JsonProperty("self")
    public First getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(First value) {
        this.self = value;
    }

    @JsonProperty("next")
    public First getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(First value) {
        this.next = value;
    }

    @JsonProperty("last")
    public First getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(First value) {
        this.last = value;
    }

    @JsonProperty("profile")
    public First getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(First value) {
        this.profile = value;
    }
}
