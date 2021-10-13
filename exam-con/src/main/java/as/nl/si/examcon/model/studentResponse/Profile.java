package as.nl.si.examcon.model.studentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
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
