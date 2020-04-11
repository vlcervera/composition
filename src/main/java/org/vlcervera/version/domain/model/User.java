package org.vlcervera.version.domain.model;

import lombok.Builder;
import lombok.Data;
import org.vlcervera.version.domain.model.vobject.UserCompany;
import org.vlcervera.version.domain.model.vobject.UserEmail;
import org.vlcervera.version.domain.model.vobject.UserName;
import org.vlcervera.version.domain.model.vobject.UserPhone;

import java.util.UUID;

@Data
@Builder
public class User {
    UUID        id;
    UserName    name;
    UserCompany company;
    UserEmail   email;
    UserPhone   phone;
}
