package org.vlcervera.composition.infrastructure.web.exception;

import java.util.UUID;

public class UserFinderException extends RuntimeException {

    public UserFinderException(UUID userId) {
        super("Can't retrieve all user information for userId = " + userId);
    }
}
