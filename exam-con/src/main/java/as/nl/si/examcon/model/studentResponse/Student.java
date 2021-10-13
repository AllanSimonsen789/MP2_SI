package as.nl.si.examcon.model.studentResponse;

import as.nl.si.examcon.model.studentResponse.StudentLinks;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private String sname;
    private String smail;
    private StudentLinks links;

    @JsonProperty("sname")
    public String getSname() {
        return sname;
    }

    @JsonProperty("sname")
    public void setSname(String value) {
        this.sname = value;
    }

    @JsonProperty("smail")
    public String getSmail() {
        return smail;
    }

    @JsonProperty("smail")
    public void setSmail(String value) {
        this.smail = value;
    }

    @JsonProperty("_links")
    public StudentLinks getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(StudentLinks value) {
        this.links = value;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sname='" + sname + '\'' +
                ", smail='" + smail + '\'' +
                ", links=" + links +
                '}';
    }
}
