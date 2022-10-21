package com.visionrent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {
    @Size(min=1,max = 50, message = "Your Name '${validatedValue}' must be between {min} and {max} chars long")
    @NotBlank(message = "Please provide a name")
    private String name;

    @Size(min=1,max = 50, message = "Subject '${validatedValue}' must be between {min} and {max} chars long")
    @NotBlank(message = "Please provide a subject")
    private String subject;

    @Size(min=1,max = 50, message = "Message Body '${validatedValue}' must be between {min} and {max} chars long")
    @NotBlank(message = "Please provide a body")
    private String body;

    @Email(message = "Please provide a Email")
    @NotBlank(message = "Please provide a email")
    private String email;
}
