package org.vlcervera.composition.infrastructure.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.port.UserNameFinderPort;
import org.vlcervera.composition.domain.vobject.UserName;
import org.vlcervera.composition.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserNameFinderAdapter implements UserNameFinderPort {

    @Override
    @Async("customTaskExecutor")
    public CompletableFuture<UserName> findConcurrent(UUID userId) {
        log.info("Start to retrieve name for user {}", userId);
        return CompletableFuture.completedFuture(generate(userId));
    }

    @Override
    public UserName find(UUID userId) {
        return generate(userId);
    }

    private UserName generate(UUID userId) {
        TimeUtilities.sleep(5);
        log.info("End retrieve name for user {}", userId);
        return new UserName("Name for " + userId.toString());
    }
}
