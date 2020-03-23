package org.vlcervera.composition.domain.vobject;

import org.jeasy.random.annotation.Randomizer;
import org.jeasy.random.randomizers.FirstNameRandomizer;

public class UserName extends ValueObject<String> {

    @Randomizer(FirstNameRandomizer.class)
    protected String value;

    public UserName(String value) {
        super(value);
    }
}
