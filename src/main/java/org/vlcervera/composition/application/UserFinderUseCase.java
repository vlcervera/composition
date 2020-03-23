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
public class UserFinderUseCase {

    private final UserNameFinderPort    userNameFinderPort;
    private final UserPhoneFinderPort   userPhoneFinderPort;
    private final UserCompanyFinderPort userCompanyFinderPORT;
    private final UserEmailFinderPort   userEmailFinderPort;

    public UserResponse find() throws ExecutionException, InterruptedException {

        UUID                           userId          = UUID.randomUUID();
        CompletableFuture<UserName>    userNamePage    = userNameFinderPort.find(userId);
        CompletableFuture<UserPhone>   userPhonePage   = userPhoneFinderPort.find(userId);
        CompletableFuture<UserCompany> userCompanyPage = userCompanyFinderPORT.find(userId);
        CompletableFuture<UserEmail>   userEmailPage   = userEmailFinderPort.find(userId);

        // Wait until they are all done
        CompletableFuture.allOf(userNamePage, userPhonePage, userCompanyPage).join();

        User user = User.builder()
                        .name(userNamePage.get())
                        .email(userEmailPage.get())
                        .company(userCompanyPage.get())
                        .phone(userPhonePage.get())
                        .build();

        return UserResponse.of(user);
    }
}
