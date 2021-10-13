package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grade {
    private long eid;
    private long sid;
    private long grade;
    private GradeLinks links;

    @JsonProperty("eid")
    public long getEid() {
        return eid;
    }

    @JsonProperty("eid")
    public void setEid(long value) {
        this.eid = value;
    }

    @JsonProperty("sid")
    public long getSid() {
        return sid;
    }

    @JsonProperty("sid")
    public void setSid(long value) {
        this.sid = value;
    }

    @JsonProperty("grade")
    public long getGrade() {
        return grade;
    }

    @JsonProperty("grade")
    public void setGrade(long value) {
        this.grade = value;
    }

    @JsonProperty("_links")
    public GradeLinks getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(GradeLinks value) {
        this.links = value;
    }
}
