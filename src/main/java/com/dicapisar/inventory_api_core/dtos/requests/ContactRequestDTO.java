package com.dicapisar.inventory_api_core.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {
    @NotEmpty(message = "The name must not be empty.")
    private String name;

    @Pattern(regexp = "^[0-9+-]*$", message = "The Phone Number must have be number from 0 to 9, '+' or '-'.")
    private String phoneNumber;

    @Email(message = "The format Email not valid")
    @Pattern(regexp=".+@.+\\..+", message="The format Email not valid")
    private String email;
}
