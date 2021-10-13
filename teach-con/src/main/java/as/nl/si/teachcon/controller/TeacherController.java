package as.nl.si.teachcon.controller;

import as.nl.si.teachcon.exceptions.TeacherNotFoundException;
import as.nl.si.teachcon.model.Teacher;
import as.nl.si.teachcon.repository.TeacherRepository;
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

@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    TeacherRepository repo;

    @GetMapping("/")
    public List<Teacher> retrieveAllTeachers()
    {
        return repo.findAll();
    }

    // This is the only method, which returns hyperlinks, for now
    // If the resource is found, a link to its 'family' is appended to its native load
    @GetMapping("/{id}")
    public EntityModel<Teacher> retrieveTeacher(@PathVariable long id)
    {
        Optional<Teacher> teacher = repo.findById(id);
        if (!teacher.isPresent())
            throw new TeacherNotFoundException("id: " + id);

        EntityModel<Teacher> resource = EntityModel.of(teacher.get()); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllTeachers()); // get link
        resource.add(linkTo.withRel("all-teachers"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveTeacher(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable long id) {
        repo.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher)
    {
        Teacher savedTeacher = repo.save(teacher);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTeacher.getTid()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher, @PathVariable long id)
    {
        Optional<Teacher> teacherOptional = repo.findById(id);
        if (!teacherOptional.isPresent())
            return ResponseEntity.notFound().build();
        teacher.setTid(id);
        repo.save(teacher);
        return ResponseEntity.noContent().build();
    }

}

