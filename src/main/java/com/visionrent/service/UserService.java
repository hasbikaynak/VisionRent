package com.visionrent.service;

import com.visionrent.domain.Role;
import com.visionrent.domain.User;
import com.visionrent.domain.enums.RoleType;
import com.visionrent.dto.request.RegisterRequest;
import com.visionrent.exception.ConflictException;
import com.visionrent.exception.ResourceNotFoundException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public void saveUser(RegisterRequest registerRequest) {

        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
        }

        Role role= roleService.findByType(RoleType.ROLE_CUSTOMER);

        Set<Role> roles=new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user =new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setAddress(registerRequest.getAddress());
        user.setEmail(registerRequest.getEmail());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setZipCode(registerRequest.getZipCode());
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,email)));
    }
}
