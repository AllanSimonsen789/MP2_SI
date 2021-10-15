package as.nl.si.gradecon.model;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Grade extends RepresentationModel<Grade> {
    @Id
    @GeneratedValue
    Long gid;
    Long eid;
    String sid;
    Long grade;

    public Grade() {
        super();
    }
}
