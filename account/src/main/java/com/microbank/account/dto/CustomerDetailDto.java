package com.microbank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
        name = "Customer Detail",
        description = "Details of a customer including account, card and loan"
)
@NoArgsConstructor
public class CustomerDetailDto extends CustomerDto {

    public CustomerDetailDto(CustomerDto customerDto, AccountDto accountDto, LoanDto loanDto, CardDto cardDto) {
        super(customerDto.getName(), customerDto.getEmail(), customerDto.getMobileNumber(), accountDto);
        this.loan = loanDto;
        this.card = cardDto;
    }

    @Schema(
            description = "Loan details of the Customer"
    )
    private LoanDto loan;

    @Schema(
            description = "Card details of the Customer"
    )
    private CardDto card;
}
