package as.nl.si.examcon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Exam {

    @Id
    @GeneratedValue
    private long eid;
    private String ename;
    private String edate;



    public Exam() {
        super();
    }
}
