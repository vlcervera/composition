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

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFinderSequentialUseCase {
    private final UserNameFinderPort    userNameFinderPort;
    private final UserPhoneFinderPort   userPhoneFinderPort;
    private final UserCompanyFinderPort userCompanyFinderPORT;
    private final UserEmailFinderPort   userEmailFinderPort;

    public UserResponse find() {

        UUID        userId      = UUID.randomUUID();
        UserName    userName    = userNameFinderPort.find(userId);
        UserPhone   userPhone   = userPhoneFinderPort.find(userId);
        UserCompany userCompany = userCompanyFinderPORT.find(userId);
        UserEmail   userEmail   = userEmailFinderPort.find(userId);

        User user = User.builder()
                        .name(userName)
                        .email(userEmail)
                        .company(userCompany)
                        .phone(userPhone)
                        .build();

        return UserResponse.of(user);
    }
}
