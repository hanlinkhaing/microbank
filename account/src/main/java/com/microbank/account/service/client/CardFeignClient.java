package com.microbank.account.service.client;

import com.microbank.account.dto.CardDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card")
public interface CardFeignClient {

    @GetMapping("/api/fetch")
    ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
}
