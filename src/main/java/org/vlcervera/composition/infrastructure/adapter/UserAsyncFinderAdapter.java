package org.vlcervera.composition.infrastructure.adapter;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.model.User;
import org.vlcervera.composition.domain.model.vobject.UserCompany;
import org.vlcervera.composition.domain.model.vobject.UserEmail;
import org.vlcervera.composition.domain.model.vobject.UserName;
import org.vlcervera.composition.domain.model.vobject.UserPhone;
import org.vlcervera.composition.domain.port.UserFinderPort;
import org.vlcervera.composition.infrastructure.exception.UserFinderException;
import org.vlcervera.composition.infrastructure.finder.UserCompanyFinder;
import org.vlcervera.composition.infrastructure.finder.UserEmailFinder;
import org.vlcervera.composition.infrastructure.finder.UserNameFinder;
import org.vlcervera.composition.infrastructure.finder.UserPhoneFinder;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAsyncFinderAdapter implements UserFinderPort {

    private final UserNameFinder    userNameFinder;
    private final UserPhoneFinder   userPhoneFinder;
    private final UserCompanyFinder userCompanyFinder;
    private final UserEmailFinder   userEmailFinder;

    public User find(UUID userId) {
        log.info("Start user finder in async mode for userId {}", userId);
        User user;
        try {
            CompletableFuture<UserName>    userNamePage    = userNameFinder.findAsync(userId);
            CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinder.findAsync(userId);
            CompletableFuture<UserCompany> userCompanyPage = userCompanyFinder.findAsync(userId);
            CompletableFuture<UserEmail>   userEmailPage   = userEmailFinder.findAsync(userId);

            CompletableFuture.allOf(userNamePage, userPhonePage, userCompanyPage, userEmailPage).join();

            user = User.builder()
                       .name(userNamePage.get())
                       .email(userEmailPage.get())
                       .company(userCompanyPage.get())
                       .phone(userPhonePage.get())
                       .build();
        } catch (Exception e) {
            log.error("Error retrieving user {}", userId, e);
            throw new UserFinderException(userId);
        }

        return user;
    }
}
