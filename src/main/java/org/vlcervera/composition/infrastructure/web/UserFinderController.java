package org.vlcervera.composition.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vlcervera.composition.application.UserFinderUseCase;
import org.vlcervera.composition.application.response.UserResponse;
import org.vlcervera.composition.infrastructure.web.exception.UserFinderException;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
public class UserFinderController {

    private final UserFinderUseCase userFinderUseCaseAsync;
    private final UserFinderUseCase userFinderUseCase;

    @GetMapping("/async")
    public UserResponse getAsync(@PathVariable("userId") UUID userId) {
        return userFinderUseCaseAsync.find(userId);
    }

    @GetMapping
    public UserResponse get(@PathVariable("userId") UUID userId) {
        return userFinderUseCase.find(userId);
    }

    /*
    EXCEPTION HANDLER
     */
    @ExceptionHandler(UserFinderException.class)
    public ResponseEntity<String> handleException(UserFinderException ex) {
        log.error("Error retrieving user information", ex);
        return ResponseEntity.status(500).body(ex.getMessage());
    }

}
