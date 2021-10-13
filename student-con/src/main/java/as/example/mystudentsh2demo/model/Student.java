package as.example.mystudentsh2demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue
    private long sid;
    private String sname;
    private String smail;

    public Student() {
        super();
    }
}
