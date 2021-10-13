package as.nl.si.examcon.model;

import lombok.Data;

@Data
public class StudentDTO {
    String sname;
    String smail;
    String _links;
    String student;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "sname='" + sname + '\'' +
                ", smail='" + smail + '\'' +
                ", _links='" + _links + '\'' +
                ", student='" + student + '\'' +
                '}';
    }
}
