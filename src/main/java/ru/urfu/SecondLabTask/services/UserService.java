package ru.urfu.SecondLabTask.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.dto.ProjectDTO;
import ru.urfu.SecondLabTask.dto.UserDTO;
import ru.urfu.SecondLabTask.model.Project;
import ru.urfu.SecondLabTask.dto.UserUpdatePasswordDTO;

import ru.urfu.SecondLabTask.model.Role;
import ru.urfu.SecondLabTask.model.User;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import ru.urfu.SecondLabTask.repository.ProjectRepository;
import ru.urfu.SecondLabTask.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(myUser.getUserName(),
                myUser.getPassword(), mapRolesToAthorities(myUser.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void addUser(UserDTO userDTO) throws Exception {

        User userFromDb = userRepository.findByUserName(userDTO.getUserName());
        if (userFromDb != null) {
            log.error("Пользователь есть в базе данных");
            throw new Exception("userDTO exist");
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void updateUserName(String userName, UserDTO userDTO) throws Exception {
        User userFromDb = userRepository.findByUserName(userName);
        if (userFromDb == null) {
            log.error("Пользователь не найден в базе данных");
            throw new Exception("user does not exist");
        }
        if (userRepository.findByUserName(userDTO.getUserName()) != null) {
            log.error("Пользователь найден в базе данных");
            throw new Exception("user exist");
        }
        if(!passwordEncoder.matches(userDTO.getPassword(), userFromDb.getPassword())) {
            log.error("Неверный пароль пользователя");
            throw new Exception("Incorrect password");
        }
        User user = findByUserName(userName);
        user.setUserName(userDTO.getUserName());
        userRepository.save(user);
    }

    public User updateUserPassword(String userName, UserUpdatePasswordDTO userUpdatePasswordDTO) throws Exception{
        User userFromDb = userRepository.findByUserName(userName);
        if (userFromDb == null) {
            log.error("Пользователь не найден в базе данных");
            throw new Exception("user does not exist");
        }
        if(!passwordEncoder.matches(userUpdatePasswordDTO.getUserOldPassword(), userFromDb.getPassword())) {
            log.error("Неверный пароль пользователя");
            throw new Exception("Incorrect password");
        }
        User user = findByUserName(userName);
        user.setPassword(userUpdatePasswordDTO.getUserNewPassword());
        userRepository.save(user);
        return user;
    }

    public void deleteUser(String userName) {
        User user = findByUserName(userName);
        userRepository.delete(user);
    }

    public void tryLogin(UserDTO userDTO) throws Exception {
        User userFromDb = userRepository.findByUserName(userDTO.getUserName());
        if (userFromDb == null) {
            log.error("Пользователь не найден в базе данных");
            throw new Exception("userDTO does not exist");
        }
        if (!passwordEncoder.matches(userDTO.getPassword(), userFromDb.getPassword())) {
            log.error("Неверный пароль пользователя");
            throw new Exception("Incorrect password");
        }
    }
    public void addProject(ProjectDTO projectDTO) throws Exception{
        Project projectFromDb = projectRepository.findByTitle(projectDTO.getTitle());
        if (projectFromDb != null) {
            throw new Exception("project exist");
        }
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        projectRepository.save(project);
    }
    public void assignProject(String userName, Long projectId) throws Exception{
        User userFromDb = userRepository.findByUserName(userName);
        if (userFromDb == null) {
            throw new Exception("project doesn't exist");
        }
        userFromDb.setProjectId(projectId);
        userRepository.save(userFromDb);
    }
}