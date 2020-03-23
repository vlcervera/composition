package org.vlcervera.composition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vlcervera.composition.domain.vobject.UserCompany;
import org.vlcervera.composition.domain.vobject.UserEmail;
import org.vlcervera.composition.domain.vobject.UserName;
import org.vlcervera.composition.domain.vobject.UserPhone;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    UUID        id;
    UserName    name;
    UserCompany company;
    UserEmail   email;
    UserPhone   phone;
}
