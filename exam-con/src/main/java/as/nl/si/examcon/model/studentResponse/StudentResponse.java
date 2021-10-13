package as.nl.si.examcon.model.studentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentResponse {

    private Embedded embedded;
    private StudentReponseLinks links;
    private Page page;

    @JsonProperty("_embedded")
    public Embedded getEmbedded() {
        return embedded;
    }

    @JsonProperty("_embedded")
    public void setEmbedded(Embedded value) {
        this.embedded = value;
    }

    @JsonProperty("_links")
    public StudentReponseLinks getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(StudentReponseLinks value) {
        this.links = value;
    }

    @JsonProperty("page")
    public Page getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Page value) {
        this.page = value;
    }


}