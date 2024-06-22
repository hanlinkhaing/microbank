package com.microbank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response"
)
public class ResponseDto {
    @Schema(
            description = "Status code",
            example = "201"
    )
    private String statusCode;

    @Schema(
            description = "Status message",
            example = "Account created successfully"
    )
    private String statusMsg;
//    private T data;
}
