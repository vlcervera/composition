package org.vlcervera.composition.infrastructure.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.port.UserPhoneFinderPort;
import org.vlcervera.composition.domain.vobject.UserPhone;
import org.vlcervera.composition.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserPhoneFinderAdapter implements UserPhoneFinderPort {

    @Override
    @Async("customTaskExecutor")
    public CompletableFuture<UserPhone> findConcurrent(UUID userId) {
        log.info("Start to retrieve phone for user {}", userId);
        return CompletableFuture.completedFuture(generate());
    }

    @Override
    public UserPhone find(UUID userId) {
        return generate();
    }

    private UserPhone generate() {
        TimeUtilities.sleep(5);
        log.info("End retrieve phone for user");
        return UserPhone.create("+3422124124");
    }
}
