package org.vlcervera.composition.infrastructure.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.port.UserEmailFinderPort;
import org.vlcervera.composition.domain.vobject.UserEmail;
import org.vlcervera.composition.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserEmailFinderAdapter implements UserEmailFinderPort {

    @Override
    @Async("customTaskExecutor")
    public CompletableFuture<UserEmail> findConcurrent(UUID userId) {
        log.info("Start retrieve email for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }

    @Override
    public UserEmail find(UUID userId) {
        return generate();
    }

    private UserEmail generate() {
        TimeUtilities.sleep(5);
        log.info("End retrieve email for user");
        return UserEmail.create("test@gm.com");
    }
}
