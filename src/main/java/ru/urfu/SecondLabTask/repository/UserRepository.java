package ru.urfu.SecondLabTask.repository;

import org.springframework.stereotype.Repository;
import ru.urfu.SecondLabTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUserName(final String username);
}