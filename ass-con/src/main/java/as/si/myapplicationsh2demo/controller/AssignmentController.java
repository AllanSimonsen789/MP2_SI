package as.si.myapplicationsh2demo.controller;

import as.si.myapplicationsh2demo.exceptions.AssignmentNotFoundException;
import as.si.myapplicationsh2demo.logic.AssignmentStudentController;
import as.si.myapplicationsh2demo.model.Assignment;
import as.si.myapplicationsh2demo.model.AssignmentDTO;
import as.si.myapplicationsh2demo.model.AssignmentStudent;
import as.si.myapplicationsh2demo.repository.AssignmentRepository;
import as.si.myapplicationsh2demo.repository.AssignmentStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ComponentScan
@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    AssignmentRepository repo;
    @Autowired
    AssignmentStudentRepository asrepo;

    @GetMapping("/")
    public List<Assignment> retrieveAllAssignments()
    {
        return repo.findAll();
    }

    @GetMapping("/{id}/notdone")
    public EntityModel<AssignmentDTO> retrieveNrOfStudentsNotCompletedAssignment(@PathVariable long id) throws IOException {
        EntityModel<Assignment> assignment = retrieveAssignment(id);
        AssignmentDTO assignmentDTO = new AssignmentDTO(0, assignment.getContent());
        AssignmentStudentController asc = new AssignmentStudentController();
        List<AssignmentStudent> assStudList = asrepo.findAll();

        assignmentDTO = asc.assignmentNotCompleted(assignmentDTO, assStudList);
        EntityModel<AssignmentDTO> assignmentEntity = EntityModel.of(assignmentDTO);
        assignmentEntity.add(assignment.getLinks());
        return assignmentEntity;
    }

    // This is the only method, which returns hyperlinks, for now
    // If the resource is found, a link to its 'family' is appended to its native load
    @GetMapping("/{id}")
    public EntityModel<Assignment> retrieveAssignment(@PathVariable long id)
    {
        Optional<Assignment> assignment = repo.findById(id);
        if (!assignment.isPresent())
            throw new AssignmentNotFoundException("id: " + id);

        EntityModel<Assignment> resource = EntityModel.of(assignment.get()); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllAssignments()); // get link
        resource.add(linkTo.withRel("all-assignments"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveAssignment(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    @DeleteMapping("/{id}")
    public void deleteAssignment(@PathVariable long id) {
        repo.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createAssignment(@RequestBody Assignment assignment)
    {
        Assignment savedAssignment = repo.save(assignment);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAssignment.getAid()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAssignment(@RequestBody Assignment assignment, @PathVariable long id)
    {
        Optional<Assignment> assignmentOptional = repo.findById(id);
        if (!assignmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        assignment.setAid(id);
        repo.save(assignment);
        return ResponseEntity.noContent().build();
    }

}
