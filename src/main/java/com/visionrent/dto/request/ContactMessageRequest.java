package com.visionrent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {
    @NotBlank(message = "Please provide a name")
    private String name;

    @NotBlank(message = "Please provide a subject")
    private String subject;

    @NotBlank(message = "Please provide a body")
    private String body;

    @NotBlank(message = "Please provide a email")
    private String email;
}
