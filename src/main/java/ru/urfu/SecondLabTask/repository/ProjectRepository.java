package ru.urfu.SecondLabTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.SecondLabTask.model.Project;
import ru.urfu.SecondLabTask.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByTitle(final String title);
//    Project findById(final Long id);
}
