package org.vlcervera.composition.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlcervera.composition.application.response.UserResponse;
import org.vlcervera.composition.domain.User;
import org.vlcervera.composition.domain.port.UserCompanyFinderPort;
import org.vlcervera.composition.domain.port.UserEmailFinderPort;
import org.vlcervera.composition.domain.port.UserNameFinderPort;
import org.vlcervera.composition.domain.port.UserPhoneFinderPort;
import org.vlcervera.composition.domain.vobject.UserCompany;
import org.vlcervera.composition.domain.vobject.UserEmail;
import org.vlcervera.composition.domain.vobject.UserName;
import org.vlcervera.composition.domain.vobject.UserPhone;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFinderConcurrentUseCase {

    private final UserNameFinderPort    userNameFinderPort;
    private final UserPhoneFinderPort   userPhoneFinderPort;
    private final UserCompanyFinderPort userCompanyFinderPort;
    private final UserEmailFinderPort   userEmailFinderPort;

    public UserResponse find() throws ExecutionException, InterruptedException {

        UUID                           userId          = UUID.randomUUID();
        CompletableFuture<UserName>    userNamePage    = userNameFinderPort.findConcurrent(userId);
        CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinderPort.findAsync(userId);
        CompletableFuture<UserCompany> userCompanyPage = userCompanyFinderPort.findConcurrent(userId);
        CompletableFuture<UserEmail>   userEmailPage   = userEmailFinderPort.findConcurrent(userId);

        /* FIXME happy path. In case of get an Exception in findConcurrent it must be handled
        In this example join() can throw a CompletionException and it is handled by ExceptionHandler defined in
        Controller */
        CompletableFuture.allOf(userNamePage, userPhonePage, userCompanyPage, userEmailPage).join();

        User user = User.builder()
                        .name(userNamePage.get())
                        .email(userEmailPage.get())
                        .company(userCompanyPage.get())
                        .phone(userPhonePage.get())
                        .build();

        return UserResponse.of(user);
    }
}
