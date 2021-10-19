package as.si.myapplicationsh2demo.model.studentResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {
    private long size;
    private long totalElements;
    private long totalPages;
    private long number;

    @JsonProperty("size")
    public long getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(long value) {
        this.size = value;
    }

    @JsonProperty("totalElements")
    public long getTotalElements() {
        return totalElements;
    }

    @JsonProperty("totalElements")
    public void setTotalElements(long value) {
        this.totalElements = value;
    }

    @JsonProperty("totalPages")
    public long getTotalPages() {
        return totalPages;
    }

    @JsonProperty("totalPages")
    public void setTotalPages(long value) {
        this.totalPages = value;
    }

    @JsonProperty("number")
    public long getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(long value) {
        this.number = value;
    }
}
