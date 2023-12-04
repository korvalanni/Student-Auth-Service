package ru.urfu.SecondLabTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.SecondLabTask.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectTitle(final String title);
}
