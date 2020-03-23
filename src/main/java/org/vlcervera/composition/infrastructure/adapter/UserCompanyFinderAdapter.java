package org.vlcervera.composition.infrastructure.adapter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.port.UserCompanyFinderPort;
import org.vlcervera.composition.domain.vobject.UserCompany;
import org.vlcervera.composition.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserCompanyFinderAdapter implements UserCompanyFinderPort {

    @Override
    @Async("taskExecutor")
    public CompletableFuture<UserCompany> find(UUID uuid) {
        log.info("Retrieve company for user {}", uuid);
        TimeUtilities.sleep(5);
        return CompletableFuture.completedFuture(new UserCompany("Company for " + uuid.toString()));
    }
}
