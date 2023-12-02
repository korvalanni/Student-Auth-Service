package ru.urfu.SecondLabTask.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.urfu.SecondLabTask.dto.UserDTO;
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
import ru.urfu.SecondLabTask.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if (userFromDb == null)
            throw new Exception("user does not exist");
        if (userRepository.findByUserName(userDTO.getUserName()) != null)
            throw new Exception("user exist");

        if(!passwordEncoder.matches(userDTO.getPassword(), userFromDb.getPassword()))
            throw new Exception("Incorrect password");

        User user = findByUserName(userName);
        user.setUserName(userDTO.getUserName());
        userRepository.save(user);
    }

    public User updateUserPassword(String userName, UserUpdatePasswordDTO userUpdatePasswordDTO) throws Exception{
        //toDo написать метод обновления пароля пользователя
        return null;
    }

    public void deleteUser(String userName) {
        User user = findByUserName(userName);
        userRepository.delete(user);
    }

    public void tryLogin(UserDTO userDTO) throws Exception {
        User userFromDb = userRepository.findByUserName(userDTO.getUserName());
        if (userFromDb == null) {
            throw new Exception("userDTO does not exist");
        }
        if (!passwordEncoder.matches(userDTO.getPassword(), userFromDb.getPassword())) {
            throw new Exception("Incorrect password");
        }
    }
}