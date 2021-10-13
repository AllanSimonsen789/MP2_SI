package as.nl.si.examcon.model;

import as.nl.si.examcon.model.gradeResponse.Grade;
import as.nl.si.examcon.model.studentResponse.Student;
import as.nl.si.examcon.model.studentResponse.StudentResponse;
import lombok.Data;

import java.util.List;


@Data
public class ExamDto {

    private long examID;
    private String examName;
    private String examDate;

    private List<Student> studentList;
    private List<Grade> gradelist;

    public ExamDto(Exam exam) {
        super();
        examID = exam.getEid();
        examName = exam.getEname();
        examDate = exam.getEdate();
    }

    public ExamDto() {
        super();
    }
}
