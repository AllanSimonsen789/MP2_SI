package as.si.myapplicationsh2demo.logic;

import as.si.myapplicationsh2demo.controller.ConverterStudent;
import as.si.myapplicationsh2demo.controller.GetResponseBody;
import as.si.myapplicationsh2demo.model.Assignment;
import as.si.myapplicationsh2demo.model.AssignmentDTO;
import as.si.myapplicationsh2demo.model.AssignmentStudent;
import as.si.myapplicationsh2demo.model.StudentDTO;
import as.si.myapplicationsh2demo.model.studentResponse.Student;
import as.si.myapplicationsh2demo.model.studentResponse.StudentResponse;
import as.si.myapplicationsh2demo.repository.AssignmentRepository;
import as.si.myapplicationsh2demo.repository.AssignmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class AssignmentStudentController {

    public AssignmentDTO assignmentNotCompleted(AssignmentDTO assignmentDTO, List<AssignmentStudent> assStudList) throws IOException {
        List<Student> studentList;
        System.out.println(assStudList);
        try {
            //return getResponseCodeForURLUsing("http://localhost:8042/exams", "GET");
            HttpURLConnection.setFollowRedirects(false); // Set follow redirects to false
            final URL url = new URL("http://localhost:8070/students");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            String jsonStudents = GetResponseBody.GetResponseBody(huc);
            StudentResponse data = ConverterStudent.fromJsonString(jsonStudents);
            studentList = new ArrayList<>(Arrays.stream(data.getEmbedded().getStudents()).toList());



            Iterator<Student> iter = studentList.iterator();
            while(iter.hasNext()) {
                Student s = iter.next();
                Iterator<AssignmentStudent> asiter = assStudList.iterator();
                while(asiter.hasNext()) {
                    AssignmentStudent as = asiter.next();
                    if (as.getAid() == assignmentDTO.getAssignmentID() && s.getSmail().equals(as.getSmail())) {
                        iter.remove();
                    }
                }
            }
            assignmentDTO.setNrNotDoneByStudents(studentList.size());
            assignmentDTO.setStudentList(studentList);

        } catch (ProtocolException | MalformedURLException e) {
            e.printStackTrace();
        }
        return assignmentDTO;

    }

}