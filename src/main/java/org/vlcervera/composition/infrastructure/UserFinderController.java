package org.vlcervera.composition.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vlcervera.composition.application.UserFinderUseCase;
import org.vlcervera.composition.application.response.UserResponse;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/{version}")
public class UserFinderController {

    private final UserFinderUseCase userFinderUseCase;

    @GetMapping("/test")
    public UserResponse get() throws ExecutionException, InterruptedException {
        return userFinderUseCase.find();
    }

}
