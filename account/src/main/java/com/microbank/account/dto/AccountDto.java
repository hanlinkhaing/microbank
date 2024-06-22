package com.microbank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Details of an account"
)
public class AccountDto {
    @Schema(
            description = "Account number"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
    @NotEmpty(message = "Account number is required")
    private Long accountNumber;

    @Schema(
            description = "Account type"
    )
    @NotEmpty(message = "Account type is required")
    private String accountType;

    @Schema(
            description = "Branch name",
            example = "Main Street"
    )
    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
