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
