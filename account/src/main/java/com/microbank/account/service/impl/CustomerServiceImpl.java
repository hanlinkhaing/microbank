package com.microbank.account.service.impl;

import com.microbank.account.dto.*;
import com.microbank.account.entity.Account;
import com.microbank.account.entity.Customer;
import com.microbank.account.exception.ResourceNotFoundException;
import com.microbank.account.mapper.AccountMapper;
import com.microbank.account.mapper.CustomerMapper;
import com.microbank.account.repository.AccountRepository;
import com.microbank.account.repository.CustomerRepository;
import com.microbank.account.service.ICustomerService;
import com.microbank.account.service.client.CardFeignClient;
import com.microbank.account.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardFeignClient cardFeignClient;
    private LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailDto getCustomerDetail(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountMapper.mapToAccountsDto(account, new AccountDto());

        ResponseEntity<LoanDto> loanDetails = loanFeignClient.fetchLoanDetails(mobileNumber);

        ResponseEntity<CardDto> cardDetails = cardFeignClient.fetchCardDetails(mobileNumber);

        return new CustomerDetailDto(customerDto, accountDto, loanDetails.getBody(), cardDetails.getBody());
    }
}
