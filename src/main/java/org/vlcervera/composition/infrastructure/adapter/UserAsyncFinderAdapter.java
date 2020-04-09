package org.vlcervera.composition.infrastructure.adapter;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.domain.User;
import org.vlcervera.composition.domain.port.UserFinderPort;
import org.vlcervera.composition.domain.vobject.UserCompany;
import org.vlcervera.composition.domain.vobject.UserEmail;
import org.vlcervera.composition.domain.vobject.UserName;
import org.vlcervera.composition.domain.vobject.UserPhone;
import org.vlcervera.composition.infrastructure.finder.UserCompanyFinder;
import org.vlcervera.composition.infrastructure.finder.UserEmailFinder;
import org.vlcervera.composition.infrastructure.finder.UserNameFinder;
import org.vlcervera.composition.infrastructure.finder.UserPhoneFinder;
import org.vlcervera.composition.infrastructure.web.exception.UserFinderException;

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
        User user;
        try {
            CompletableFuture<UserName>    userNamePage    = userNameFinder.findAsync(userId);
            CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinder.findAsync(userId);
            CompletableFuture<UserCompany> userCompanyPage = userCompanyFinder.findAsync(userId);
            CompletableFuture<UserEmail>   userEmailPage   = userEmailFinder.findAsync(userId);

            /* FIXME happy path. In case of get an Exception in findAsync it must be handled
            In this example join() can throw a CompletionException and it is handled by ExceptionHandler defined in
            Controller */
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
