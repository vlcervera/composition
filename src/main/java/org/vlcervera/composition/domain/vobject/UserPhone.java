package org.vlcervera.composition.domain.vobject;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.randomizers.PhoneNumberRandomizer;

public class UserPhone extends ValueObject<String> {

    @Randomizer(PhoneNumberRandomizer.class)
    protected String value;

    public UserPhone(String value) {
        super(value);
    }
}
