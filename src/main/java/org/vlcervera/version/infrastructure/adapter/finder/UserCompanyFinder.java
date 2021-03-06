package org.vlcervera.version.infrastructure.adapter.finder;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.vlcervera.version.domain.model.vobject.UserCompany;
import org.vlcervera.version.utils.TimeUtilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserCompanyFinder {

    @Async("customTaskExecutor")
    public CompletableFuture<UserCompany> findAsync(UUID userId) {
        log.info("Start retrieve company for user {}", userId);
        return CompletableFuture.completedFuture(generate(userId));
    }

    public UserCompany find(UUID userId) {
        return generate(userId);
    }

    private UserCompany generate(UUID userId) {
        TimeUtilities.sleep(5);
        log.info("End retrieve company for user {}", userId);
        return new UserCompany("Company for " + userId.toString());
    }
}
