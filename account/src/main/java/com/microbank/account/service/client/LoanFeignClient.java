package com.microbank.account.service.client;

import com.microbank.account.dto.LoanDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loan")
public interface LoanFeignClient {
    @GetMapping("/api/fetch")
    ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
