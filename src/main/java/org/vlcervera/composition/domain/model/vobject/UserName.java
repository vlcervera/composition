package org.vlcervera.composition.domain.model.vobject;

public class UserName extends ValueObject<String> {

    protected String value;

    public UserName(String value) {
        super(value);
    }
}
