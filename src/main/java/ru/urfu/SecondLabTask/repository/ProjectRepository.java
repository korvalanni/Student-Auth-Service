package ru.urfu.SecondLabTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.SecondLabTask.model.Project;
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
