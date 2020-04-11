package org.vlcervera.version.infrastructure.adapter.finder;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.version.domain.model.vobject.UserEmail;
import org.vlcervera.version.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserEmailFinder {

    @Async("customTaskExecutor")
    public CompletableFuture<UserEmail> findAsync(UUID userId) {
        log.info("Start retrieve email for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }

    public UserEmail find(UUID userId) {
        return generate();
    }

    private UserEmail generate() {
        TimeUtilities.sleep(5);
        log.info("End retrieve email for user");
        return UserEmail.create("test@gm.com");
    }
}
