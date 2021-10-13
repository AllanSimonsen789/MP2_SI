package as.si.myapplicationsh2demo.controller;

import as.si.myapplicationsh2demo.exceptions.AssignmentNotFoundException;
import as.si.myapplicationsh2demo.model.Assignment;
import as.si.myapplicationsh2demo.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    AssignmentRepository repo;

    @GetMapping("/")
    public List<Assignment> retrieveAllAssignements()
    {
        return repo.findAll();
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
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllAssignements()); // get link
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
