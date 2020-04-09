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
    @Async("customTaskExecutor")
    public CompletableFuture<UserCompany> findConcurrent(UUID userId) {
        log.info("Start retrieve company for user {}", userId);
        return CompletableFuture.completedFuture(generate(userId));
    }

    @Override
    public UserCompany find(UUID userId) {
        return generate(userId);
    }

    private UserCompany generate(UUID userId) {
        TimeUtilities.sleep(5);
        log.info("End retrieve company for user {}", userId);
        return new UserCompany("Company for " + userId.toString());
    }
}
