package de.structuremade.ms.schoolservice.api.json;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSchoolJson {

    @NotBlank(message = "Schoolname is required")
    private String name;

    @Email(message = "Valid Email is required")
    private String email;
}
