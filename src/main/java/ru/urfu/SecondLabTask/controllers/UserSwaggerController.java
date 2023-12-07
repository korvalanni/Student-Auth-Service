package ru.urfu.SecondLabTask.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.dto.ProjectDTO;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.dto.UserUpdatePasswordDTO;
import ru.urfu.SecondLabTask.model.User;
import ru.urfu.SecondLabTask.repository.ProjectRepository;
import ru.urfu.SecondLabTask.services.UserService;
@Slf4j
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
        log.info("Пользователь был успешно найден");
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
        log.info("Пользователь был успешно создан");
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
        log.info("Логин пользователя был успешно обновлен");
        return ResponseEntity.ok().build();
    }
    @PutMapping("/password/{userName}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable String userName, @RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO){
        try{
            userService.updateUserPassword(userName, userUpdatePasswordDTO);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        log.info("Пароль пользователя был успешно обновлен");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        log.info("Пользователь был успешно удален");
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
