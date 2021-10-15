package as.nl.si.examcon.controller;


import as.nl.si.examcon.model.gradeResponse.GradeReponse;
import as.nl.si.examcon.repository.ExamRepository;
import as.nl.si.examcon.exception.ExamNotFoundException;
import as.nl.si.examcon.model.Exam;
import as.nl.si.examcon.model.ExamDto;
import as.nl.si.examcon.model.studentResponse.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static as.nl.si.examcon.logic.ExamGradeController.examPassed;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    ExamRepository repo;

    @GetMapping("/")
    public List<Exam> retrieveAllExams() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Exam> retrieveExam(@PathVariable long id) {
        Optional<Exam> exam = repo.findById(id);
        if (!exam.isPresent())
            throw new ExamNotFoundException("id: " + id);

        EntityModel<Exam> resource = EntityModel.of(exam.get());                        // get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllExams()); // get link
        resource.add(linkTo.withRel("all-exams"));                                        // append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveExam(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    @GetMapping("/{id}/students")
    public EntityModel<ExamDto> retrieveExamResultsForStudents(@PathVariable long id) throws IOException {
        EntityModel<Exam> exam = retrieveExam(id);
        ExamDto examDto = new ExamDto(exam.getContent());

        examDto = examPassed(examDto);
        EntityModel<ExamDto> examEntity = EntityModel.of(examDto);
        examEntity.add(exam.getLinks());
        return examEntity;

    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable long id) {
        repo.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createExam(@RequestBody Exam exam) {
        Exam savedExam = repo.save(exam);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedExam.getEid()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExam(@RequestBody Exam exam, @PathVariable long id) {
        Optional<Exam> studentOptional = repo.findById(id);
        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();
        exam.setEid(id);
        repo.save(exam);
        return ResponseEntity.noContent().build();
    }
}