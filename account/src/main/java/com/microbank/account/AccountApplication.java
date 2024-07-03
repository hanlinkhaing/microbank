package com.microbank.account;

import com.microbank.account.dto.ContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
/*@ComponentScans({
        @ComponentScan("com.microbank.account.controller"),
        @ComponentScan("com.microbank.account.service"),
        @ComponentScan("com.microbank.account.repository"),
        @ComponentScan("com.microbank.account.audit")

})*/
/*@EnableJpaRepositories(basePackages = "com.microbank.account.repository")*/
/*@EntityScan(basePackages = "com.microbank.account.entity")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account Service",
                version = "1.0",
                description = "MicroBank Account Service v1.0",
                contact = @Contact(
                        name = "Smiles",
                        email = "dev@microbank.com",
                        url = "https://microbank.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "MicroBank API Documentation",
                url = "https://doc.microbank.com"
        )
)
@EnableConfigurationProperties(value = {ContactInfoDto.class})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
