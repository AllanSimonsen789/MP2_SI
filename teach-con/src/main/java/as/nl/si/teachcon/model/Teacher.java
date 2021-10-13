package as.nl.si.teachcon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    Long tid;
    String tname;
    String tmail;

    public Teacher() {
        super();
    }
}
