package as.nl.si.gradecon.controller;

import as.nl.si.gradecon.exceptions.GradeNotFoundException;
import as.nl.si.gradecon.model.Grade;
import as.nl.si.gradecon.repository.GradeRepository;
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

@RequestMapping("/grades")
public class GradeController {
    @Autowired
    GradeRepository repo;

    @GetMapping("/")
    public List<Grade> retrieveAllGrades()
    {
        return repo.findAll();
    }

    // This is the only method, which returns hyperlinks, for now
    // If the resource is found, a link to its 'family' is appended to its native load
    @GetMapping("/{id}")
    public EntityModel<Grade> retrieveGrade(@PathVariable long id)
    {
        Optional<Grade> grade = repo.findById(id);
        if (!grade.isPresent())
            throw new GradeNotFoundException("id: " + id);

        EntityModel<Grade> resource = EntityModel.of(grade.get()); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllGrades()); // get link
        resource.add(linkTo.withRel("all-grades"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveGrade(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable long id) {
        repo.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createGrade(@RequestBody Grade grade)
    {
        Grade savedGrade = repo.save(grade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedGrade.getGid()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGrade(@RequestBody Grade grade, @PathVariable long id)
    {
        Optional<Grade> gradeOptional = repo.findById(id);
        if (!gradeOptional.isPresent())
            return ResponseEntity.notFound().build();
        grade.setGid(id);
        repo.save(grade);
        return ResponseEntity.noContent().build();
    }

}
