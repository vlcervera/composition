package org.vlcervera.composition.domain.vobject;

public class UserName extends ValueObject<String> {

    protected String value;

    public UserName(String value) {
        super(value);
    }
}
