package as.si.myapplicationsh2demo.logic;

import as.si.myapplicationsh2demo.controller.ConverterStudent;
import as.si.myapplicationsh2demo.model.AssignmentDTO;
import as.si.myapplicationsh2demo.model.AssignmentStudent;
import as.si.myapplicationsh2demo.model.studentResponse.Student;
import as.si.myapplicationsh2demo.model.studentResponse.StudentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class AssignmentStudentController {



    public AssignmentDTO assignmentNotCompleted(AssignmentDTO assignmentDTO, List<AssignmentStudent> assStudList, RestTemplate restTemplate) throws IOException {
        List<Student> studentList;
        System.out.println(assStudList);
        try {
            String jsonStudents = restTemplate.getForObject("http://MY-STUDENTS:8070/students", String.class);
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