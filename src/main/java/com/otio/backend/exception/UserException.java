package com.otio.backend.exception;

public class UserException extends RuntimeException {
    // Possible reasons for a user exception:
    // Username or password is empty
    // Password does not satisfy some constraints regarding size
    // Wrong or inconsistent credentials

    public UserException(String message) {
        super(message);
    }
}
