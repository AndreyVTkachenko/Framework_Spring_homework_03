package ru.gb.repository;

import org.springframework.stereotype.Repository;
import ru.gb.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Project> getAll() {
        return List.copyOf(projects);
    }

    public Project create(Project project) {
        project.setId(sequence++);
        projects.add(project);
        return project;
    }

    public void delete(Long id) {
        projects.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(projects::remove);
    }
}

