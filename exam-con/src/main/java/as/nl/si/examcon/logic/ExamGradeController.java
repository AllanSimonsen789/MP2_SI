package as.nl.si.examcon.logic;

import as.nl.si.examcon.controller.ConverterGrade;
import as.nl.si.examcon.controller.ConverterStudent;
import as.nl.si.examcon.controller.GetResponseBody;
import as.nl.si.examcon.model.ExamDto;
import as.nl.si.examcon.model.StudentDTO;
import as.nl.si.examcon.model.gradeResponse.Grade;
import as.nl.si.examcon.model.gradeResponse.GradeReponse;
import as.nl.si.examcon.model.studentResponse.Student;
import as.nl.si.examcon.model.studentResponse.StudentResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExamGradeController {

    public static ExamDto examPassed(ExamDto examDto) throws IOException {
        System.out.println("Hello There");
        List<StudentDTO> studDTOList = new ArrayList<>();
        List<Student> studentList;
        List<Grade> gradeList;
        try {
            //return getResponseCodeForURLUsing("http://localhost:8042/exams", "GET");
            HttpURLConnection.setFollowRedirects(false); // Set follow redirects to false
            System.out.println("http://students:8070/students");
            final URL url = new URL("http://students:8070/students");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            String jsonStudents = GetResponseBody.GetResponseBody(huc);
            StudentResponse data = ConverterStudent.fromJsonString(jsonStudents);
            studentList = new ArrayList<>(Arrays.stream(data.getEmbedded().getStudents()).toList());

            //Getgrades
            HttpURLConnection.setFollowRedirects(false); // Set follow redirects to false
            final URL gradeUrl = new URL("http://grades:8040/grades");
            HttpURLConnection guc = (HttpURLConnection) gradeUrl.openConnection();
            guc.setRequestMethod("GET");
            String jsonGrades = GetResponseBody.GetResponseBody(guc);
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
