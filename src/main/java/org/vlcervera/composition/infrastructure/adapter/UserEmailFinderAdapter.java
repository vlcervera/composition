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
    @Async("taskExecutor")
    public CompletableFuture<UserEmail> find(UUID uuid) {
        log.info("Retrieve email for user {}", uuid);
        TimeUtilities.sleep(5);
        return CompletableFuture.completedFuture(new UserEmail("test@gm.com"));
    }
}
