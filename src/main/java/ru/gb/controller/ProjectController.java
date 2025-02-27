package ru.gb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.model.Project;
import ru.gb.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> project = service.getById(id);

        if (project.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(project.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = service.create(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
