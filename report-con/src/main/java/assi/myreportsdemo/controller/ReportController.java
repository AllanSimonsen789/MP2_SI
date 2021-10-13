package assi.myreportsdemo.controller;

import assi.myreportsdemo.Repository.ReportRepository;
import assi.myreportsdemo.exception.ReportNotFoundException;
import assi.myreportsdemo.model.Report;
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
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportRepository repo;

    @GetMapping("/")
    public List<Report> retrieveAllReports()
    {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Report> retrieveReport(@PathVariable long id)
    {
        Optional<Report> assignment = repo.findById(id);
        if (!assignment.isPresent())
            throw new ReportNotFoundException("id: " + id);

        EntityModel<Report> resource = EntityModel.of(assignment.get()); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllReports()); // get link
        resource.add(linkTo.withRel("all-assignments"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).retrieveReport(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return resource;
    }

    @DeleteMapping("/{id}")
    public void deleteReport (@PathVariable long id) {
        repo.deleteById(id);
    }

    // Create a new resource and remember its unique location in the hypermedia
    @PostMapping("/")
    public ResponseEntity<Object> createReport(@RequestBody Report report)
    {
        Report savedReport = repo.save(report);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(report.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReport(@RequestBody Report report, @PathVariable long id)
    {
        Optional<Report> assignmentOptional = repo.findById(id);
        if (!assignmentOptional.isPresent())
            return ResponseEntity.notFound().build();
        report.setId(id);
        repo.save(report);
        return ResponseEntity.noContent().build();
    }

}
