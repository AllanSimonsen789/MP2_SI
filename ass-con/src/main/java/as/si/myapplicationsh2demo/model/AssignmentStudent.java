package as.si.myapplicationsh2demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AssignmentStudent {

    @Id
    @GeneratedValue
    private Long asid;
    private Long aid;
    private String smail;

    public AssignmentStudent() { super(); }
}
