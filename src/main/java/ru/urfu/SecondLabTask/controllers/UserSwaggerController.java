package ru.urfu.SecondLabTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.dto.ProjectDTO;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.model.User;
import ru.urfu.SecondLabTask.repository.ProjectRepository;
import ru.urfu.SecondLabTask.services.UserService;

@RestController
@RequestMapping("/user")
public class UserSwaggerController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("{userName}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        UserDTO userDTO = new UserDTO(user.getUserName(), user.getPassword());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        try{
        userService.addUser(userDTO);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{userName}")
    public ResponseEntity<Void> updateUserName(@PathVariable String userName, @RequestBody UserDTO userDTO) {
        try{
        userService.updateUserName(userName, userDTO);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping("/password")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UserDTO userDTO){
        //toDo написать метод обновления пароля

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/project")
    public ResponseEntity<Long> createProject(@RequestBody ProjectDTO projectDTO){
        try{
            userService.addProject(projectDTO);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
        Long id = projectRepository.findByTitle(projectDTO.getTitle()).getId();
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PostMapping("/project/{projectId}")
    public ResponseEntity<ProjectDTO> assignProject(@PathVariable Long projectId, @RequestBody UserDTO userDTO){
        try{
            userService.assignProject(userDTO.getUserName(), projectId);
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
