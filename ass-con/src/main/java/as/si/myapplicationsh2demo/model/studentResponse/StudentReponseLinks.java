package as.si.myapplicationsh2demo.model.studentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentReponseLinks {
    private Profile self;
    private Profile profile;

    @JsonProperty("self")
    public Profile getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Profile value) {
        this.self = value;
    }

    @JsonProperty("profile")
    public Profile getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(Profile value) {
        this.profile = value;
    }
}
