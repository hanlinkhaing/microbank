package com.microbank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Details of an error response"
)
public class ErrorResponse {
    @Schema(
            description = "HTTP status code"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message"
    )
    private String errorMsg;

    @Schema(
            description = "API path"
    )
    private String apiPath;

    @Schema(
            description = "Error time"
    )
    private LocalDateTime errorTime;
}
