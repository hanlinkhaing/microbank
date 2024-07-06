package com.microbank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean()
	public RouteLocator routeConfig(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r ->
						r.path("/api/account/**")
								.filters(f ->
										f.rewritePath("/api/account/(?<segment>.*)", "/${segment}"))
								.uri("lb://ACCOUNT")
				)
				.route(r ->
						r.path("/api/loan/**")
								.filters(f ->
										f.rewritePath("/api/loan/(?<segment>.*)", "/${segment}"))
								.uri("lb://LOAN")
				)
				.route(r ->
						r.path("/api/card/**")
								.filters(f ->
										f.rewritePath("/api/card/(?<segment>.*)", "/${segment}"))
								.uri("lb://CARD")
				).build();
	}

}
