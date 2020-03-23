package org.vlcervera.composition.domain.vobject;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.randomizers.EmailRandomizer;

public class UserEmail extends ValueObject<String> {

    @Randomizer(EmailRandomizer.class)
    protected String value;

    public UserEmail(String value) {
        super(value);
    }
}
