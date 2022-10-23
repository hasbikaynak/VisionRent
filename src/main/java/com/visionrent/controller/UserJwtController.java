package com.visionrent.controller;

import com.visionrent.domain.User;
import com.visionrent.dto.request.RegisterRequest;
import com.visionrent.dto.response.ResponseMessage;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserJwtController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<VRResponse> registerUser(@Valid @RequestBody RegisterRequest request){
        userService.saveUser(request);

        VRResponse response = new VRResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
