package com.microbank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
        name = "Customer",
        description = "Details of a customer"
)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "johndoe@gmail.com"
    )
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "9876543210"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Address of the customer"
    )
    private AccountDto account;
}
