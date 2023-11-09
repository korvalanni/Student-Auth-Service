package ru.urfu.SecondLabTask.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.urfu.SecondLabTask.dto.UserDTO;
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
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
       User myUser = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(myUser.getUserName(),
                myUser.getPassword(), mapRolesToAthorities(myUser.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles)
    {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void addUser(UserDTO userDTO) throws Exception
    {

        User userFromDb = userRepository.findByUserName(userDTO.getUserName());
        if (userFromDb != null)
        {
            throw new Exception("userDTO exist");
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
    }
}