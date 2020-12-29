package com.kyurao.atio.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
