package as.nl.si.examcon.logic;

import as.nl.si.examcon.controller.ConverterGrade;
import as.nl.si.examcon.controller.ConverterStudent;
import as.nl.si.examcon.model.ExamDto;
import as.nl.si.examcon.model.StudentDTO;
import as.nl.si.examcon.model.gradeResponse.Grade;
import as.nl.si.examcon.model.gradeResponse.GradeReponse;
import as.nl.si.examcon.model.studentResponse.Student;
import as.nl.si.examcon.model.studentResponse.StudentResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExamGradeController {

    public static ExamDto examPassed(ExamDto examDto, RestTemplate restTemplate) throws IOException {
        List<StudentDTO> studDTOList = new ArrayList<>();
        List<Student> studentList;
        List<Grade> gradeList;
        try {
            String jsonStudents = restTemplate.getForObject("http://MY-STUDENTS:8070/students", String.class);
            StudentResponse data = ConverterStudent.fromJsonString(jsonStudents);
            studentList = new ArrayList<>(Arrays.stream(data.getEmbedded().getStudents()).toList());

            //Getgrades
            String jsonGrades = restTemplate.getForObject("http://MY-GRADES:8040/grades", String.class);
            GradeReponse gdata = ConverterGrade.fromJsonString(jsonGrades);
            gradeList = new ArrayList<>(Arrays.stream(gdata.getEmbedded().getGrades()).toList());

            Iterator<Grade> iter = gradeList.iterator();
            while(iter.hasNext()) {
                Grade g = iter.next();
                if (g.getGrade() <= 0 || g.getEid() != examDto.getExamID()) {
                    iter.remove();
                }
            }

            for (Grade grade : gradeList) {
                for (Student student: studentList) {
                    if (student.getSmail().equals(grade.getSid())){
                        studDTOList.add(new StudentDTO(grade, student));
                    }
                }
            }

        } catch (ProtocolException | MalformedURLException e) {
            e.printStackTrace();
        }
        examDto.setStudentList(studDTOList);
        return examDto;
    }

}
