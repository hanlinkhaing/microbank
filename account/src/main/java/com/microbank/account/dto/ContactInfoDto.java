package com.microbank.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "account")
@Setter
@Getter
public class ContactInfoDto {
    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}
