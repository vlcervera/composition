package org.vlcervera.composition.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vlcervera.composition.application.UserFinderConcurrentUseCase;
import org.vlcervera.composition.application.UserFinderSequentialUseCase;
import org.vlcervera.composition.application.response.UserResponse;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserFinderController {

    private final UserFinderConcurrentUseCase userFinderConcurrentUseCase;
    private final UserFinderSequentialUseCase userFinderSequentialUseCase;

    @GetMapping("/concurrent")
    public UserResponse getConcurrent() throws ExecutionException, InterruptedException {
        return userFinderConcurrentUseCase.find();
    }

    @GetMapping("/sequential")
    public UserResponse getSequential() {
        return userFinderSequentialUseCase.find();
    }

    /*
    EXCEPTION HANDLER
     */
    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<String> handleException(CompletionException ex) {
        log.error("Error retrieving user information", ex);
        return ResponseEntity.status(500).body("Can't retrieve all user information");
    }

}
