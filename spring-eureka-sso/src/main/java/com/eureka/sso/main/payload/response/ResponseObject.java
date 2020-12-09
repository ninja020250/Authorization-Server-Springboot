package com.eureka.sso.main.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.eureka.sso.main.payload.response.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObject<T> {
    private ResponseStatus status;
    private Integer statusCode;
    private String message;
    private String messageCode;
    private T data;
}
