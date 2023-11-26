package ru.urfu.SecondLabTask.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.model.User;
import ru.urfu.SecondLabTask.services.UserService;

@RestController
@RequestMapping("/user")
public class UserSwaggerController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable String userName) {
        try {
            User user = userService.findByUserName(userName);
            if (user == null) {
                //toDo добавить логирование
                return ResponseEntity.notFound().build();
            }
            UserDTO userDTO = new UserDTO(user.getUserName(), user.getPassword());
            return ResponseEntity.ok(userDTO);
        } catch (Exception ex) {
            //toDo добавить логирование
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.addUser(userDTO);
            //toDo добавить логирование
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            //toDo добавить логирование
            System.out.println(ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserName(@PathVariable String userName, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUserName(userName, userDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            //toDo добавить логирование
            System.out.println(ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUserPassword(@RequestBody UserDTO userDTO) {
        // ToDo: Implement password update logic
        System.out.println("Password update method not implemented yet.");
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        try {
            userService.deleteUser(userName);
            //toDo добавить логирование
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            //toDo добавить логирование
            System.out.println(ex);
            return ResponseEntity.notFound().build();
        }
    }
}

