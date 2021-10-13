package as.nl.si.examcon.model.gradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class First {
    private String href;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String value) {
        this.href = value;
    }
}
