package ru.urfu.SecondLabTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.dto.UserUpdatePasswordDTO;
import ru.urfu.SecondLabTask.model.User;
import ru.urfu.SecondLabTask.services.UserService;

@RestController
@RequestMapping("/user")
public class UserSwaggerController {

    @Autowired
    private UserService userService;

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
    @PutMapping("/password/{userName}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable String userName, @RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO){
        try{
            userService.updateUserPassword(userName, userUpdatePasswordDTO);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }

}
