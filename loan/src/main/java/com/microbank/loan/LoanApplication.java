package com.microbank.loan;

import com.microbank.loan.dto.ContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.microbank.loan.controller") })
@EnableJpaRepositories("com.microbank.loan.repository")
@EntityScan("com.microbank.loan.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "MicroBank Loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Han Lin Khaing",
						email = "hanlinkhaing300695@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
@EnableConfigurationProperties(value = {ContactInfoDto.class})
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}
}
