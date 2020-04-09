package org.vlcervera.composition.infrastructure.exception;

import java.util.UUID;

public class UserFinderException extends RuntimeException {

    public UserFinderException(UUID userId) {
        super("Can't retrieve all user information for userId = " + userId);
    }
}
