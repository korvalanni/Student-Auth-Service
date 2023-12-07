package ru.urfu.SecondLabTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_projects")
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String title;
//    @OneToMany
//    private List<User> users;
    public Project (String title){
        this.title = title;
    }
}
