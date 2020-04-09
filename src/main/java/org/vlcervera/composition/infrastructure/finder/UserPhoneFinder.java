package org.vlcervera.composition.infrastructure.finder;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.model.vobject.UserPhone;
import org.vlcervera.composition.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserPhoneFinder {

    @Async("customTaskExecutor")
    public CompletableFuture<UserPhone> findAsync(UUID userId) {
        log.info("Start to retrieve phone for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }

    public UserPhone find(UUID userId) {
        return generate();
    }

    private UserPhone generate() {
        TimeUtilities.sleep(5);
        log.info("End retrieve phone for user");
        return UserPhone.create("+3422124124");
    }
}
