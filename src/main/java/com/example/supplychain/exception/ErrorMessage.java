package com.example.supplychain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    Integer httpStatusCode;

    String httpStatus;

    String errorMessage;

}
