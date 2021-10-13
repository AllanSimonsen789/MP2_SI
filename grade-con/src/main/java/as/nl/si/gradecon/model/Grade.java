package as.nl.si.gradecon.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue
    Long gid;
    Long eid;
    Long sid;
    Long grade;

    public Grade() {
        super();
    }
}
