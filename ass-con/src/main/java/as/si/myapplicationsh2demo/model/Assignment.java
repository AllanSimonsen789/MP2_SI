package as.si.myapplicationsh2demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Assignment {

    @Id
    @GeneratedValue
    private long aid;
    private String aname;
    private int astudypoints;


    public Assignment() {
        super();
    }
}


