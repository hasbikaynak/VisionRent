package com.visionrent.service;

import com.visionrent.domain.Role;
import com.visionrent.domain.enums.RoleType;
import com.visionrent.exception.ResourceNotFoundException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Role findByType(RoleType type){
       return roleRepository.findByType(type).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE,type)));
    }

}
