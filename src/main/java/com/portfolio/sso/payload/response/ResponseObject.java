package com.portfolio.sso.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portfolio.sso.payload.response.enums.ResponseStatus;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PagingObject pagination;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SortingObject sorting;
}
