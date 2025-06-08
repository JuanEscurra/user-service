package com.bank_example.user_service.shared.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DataNotFoundException extends RuntimeException {

    public static final String TITLE = "Data not found";
    public static final int STATUS = 404;

    private final String message;
    private final LocalDateTime creationDate = LocalDateTime.now();

    public DataNotFoundException() {
        this.message = "No requested record found";
    }

    public DataNotFoundException(Number identity) {
        this.message = String.format("No data found with identifier %d", identity.intValue());
    }

    public DataNotFoundException(String message) {
        this.message = message;
    }

}
