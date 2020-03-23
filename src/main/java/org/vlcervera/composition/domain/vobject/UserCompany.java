package org.vlcervera.composition.domain.vobject;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.randomizers.CompanyRandomizer;

public class UserCompany extends ValueObject<String> {

    @Randomizer(CompanyRandomizer.class)
    protected String value;

    public UserCompany(String value) {
        super(value);
    }
}
