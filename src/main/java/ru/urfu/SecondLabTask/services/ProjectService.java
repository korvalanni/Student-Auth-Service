package ru.urfu.SecondLabTask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.dto.ProjectDTO;
import ru.urfu.SecondLabTask.model.Project;
import ru.urfu.SecondLabTask.model.User;
import ru.urfu.SecondLabTask.repository.ProjectRepository;
import ru.urfu.SecondLabTask.repository.UserRepository;

@Service
public class ProjectService {
   
    private final ProjectRepository projectRepository;   
    private final UserRepository userRepository;
    
    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public String addProject(ProjectDTO projectDTO) throws Exception{
        Project projectFromDb = projectRepository.findByProjectTitle(projectDTO.getTitle());
        if (projectFromDb != null) {
            throw new Exception("projectDTO exist");
        }
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        projectRepository.save(project);
        return project.getTitle();
    }
    public void assignProject(String userName, String projectTitle) throws Exception{
        User userFromDb = userRepository.findByUserName(userName);
        if (userFromDb == null) {
            throw new Exception("userDTO doesn't exist");
        }
        Long projectId = projectRepository.findByProjectTitle(projectTitle).getId();
        userFromDb.setProjectId(projectId);
        userRepository.save(userFromDb);
    }
}
