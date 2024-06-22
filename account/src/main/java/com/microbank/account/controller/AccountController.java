package com.microbank.account.controller;

import com.microbank.account.constants.AccountConstants;
import com.microbank.account.dto.CustomerDto;
import com.microbank.account.dto.ResponseDto;
import com.microbank.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(name = "MicroBank Account REST APIs", description = "APIs for managing customer accounts")
public class AccountController {

    private IAccountService accountService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new account",
            description = "Create a new account for a customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    @Operation(
            summary = "Fetch account details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details fetched successfully"
    )
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.fetchAccountDetails(mobileNumber));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details updated successfully"
            ),
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_417,
                    description = AccountConstants.MESSAGE_417_UPDATE
            )
    })
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully"
            ),
            @ApiResponse(
                    responseCode = AccountConstants.STATUS_417,
                    description = AccountConstants.MESSAGE_417_DELETE
            )
    })
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }
}
