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
    @Async("taskExecutor")
    public CompletableFuture<UserPhone> find(UUID uuid) {
        log.info("Retrieve phone for user {}", uuid);
        TimeUtilities.sleep(5);
        return CompletableFuture.completedFuture(new UserPhone("+3422124124"));
    }
}
