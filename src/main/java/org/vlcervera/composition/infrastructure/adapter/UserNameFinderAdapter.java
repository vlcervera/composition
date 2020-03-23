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
    @Async("taskExecutor")
    public CompletableFuture<UserName> find(UUID uuid) {
        log.info("Retrieve name for user {}", uuid);
        TimeUtilities.sleep(5);

        //
        return CompletableFuture.completedFuture(new UserName("Name for " + uuid.toString()));
    }
}
