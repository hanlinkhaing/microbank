package com.microbank.account.service;

import com.microbank.account.dto.CustomerDetailDto;

public interface ICustomerService {
    CustomerDetailDto getCustomerDetail(String mobileNumber);
}
