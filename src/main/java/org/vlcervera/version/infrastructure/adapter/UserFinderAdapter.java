package org.vlcervera.version.infrastructure.adapter;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vlcervera.version.domain.model.User;
import org.vlcervera.version.domain.model.vobject.UserCompany;
import org.vlcervera.version.domain.model.vobject.UserEmail;
import org.vlcervera.version.domain.model.vobject.UserName;
import org.vlcervera.version.domain.model.vobject.UserPhone;
import org.vlcervera.version.domain.port.UserFinderPort;
import org.vlcervera.version.infrastructure.adapter.finder.UserCompanyFinder;
import org.vlcervera.version.infrastructure.adapter.finder.UserEmailFinder;
import org.vlcervera.version.infrastructure.adapter.finder.UserNameFinder;
import org.vlcervera.version.infrastructure.adapter.finder.UserPhoneFinder;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserFinderAdapter implements UserFinderPort {

    private final UserNameFinder    userNameFinder;
    private final UserPhoneFinder   userPhoneFinder;
    private final UserCompanyFinder userCompanyFinder;
    private final UserEmailFinder   userEmailFinder;

    public User find(UUID userId) {
        log.info("Start user finder in sequential mode for userId {}", userId);
        UserName    userName    = userNameFinder.find(userId);
        UserPhone   userPhone   = userPhoneFinder.find(userId);
        UserCompany userCompany = userCompanyFinder.find(userId);
        UserEmail   userEmail   = userEmailFinder.find(userId);

        return User.builder()
                   .name(userName)
                   .email(userEmail)
                   .company(userCompany)
                   .phone(userPhone)
                   .build();

    }
}
