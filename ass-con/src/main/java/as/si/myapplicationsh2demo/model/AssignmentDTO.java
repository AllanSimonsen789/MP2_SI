package as.si.myapplicationsh2demo.model;

import as.si.myapplicationsh2demo.model.studentResponse.Student;
import lombok.Data;

import java.util.List;

@Data
public class AssignmentDTO {

    private long assignmentID;
    private String assignmentName;
    private int assignmentStudypoint;
    private int nrNotDoneByStudents;

    private List<Student> studentList;

    public AssignmentDTO(int notDoneNr, Assignment assignment) {
        super();
        assignmentID = assignment.getAid();
        assignmentName = assignment.getAname();
        assignmentStudypoint = assignment.getAstudypoints();
        nrNotDoneByStudents = notDoneNr;
    }

    public AssignmentDTO() {
        super();
    }
}