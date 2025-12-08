package org.example.gameconnectbackend.exceptions;

import lombok.Getter;

import java.util.Map;


@Getter
public class SameCredentialsException extends RuntimeException {
    private final Map<String,String> credentialsExist;

    public SameCredentialsException(Map<String,String> credentialsExist) {
        super("Credentials already exist");
        this.credentialsExist = credentialsExist;
    }

}
