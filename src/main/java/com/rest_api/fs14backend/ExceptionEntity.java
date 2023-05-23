package com.rest_api.fs14backend;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionEntity {
    int statusCode;
    String message;
}

