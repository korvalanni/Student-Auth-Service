package ru.urfu.SecondLabTask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.urfu.SecondLabTask.model.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDTO {
    private String title;
    private List<User> users;
}
