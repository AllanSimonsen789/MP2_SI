package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GradeReponse {
    private Embedded embedded;
    private GradeReponseLinks links;
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
    public GradeReponseLinks getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(GradeReponseLinks value) {
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
