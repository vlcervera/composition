package org.vlcervera.composition.domain.vobject;

public class UserPhone extends ValueObject<String> {

    private UserPhone(String value) {
        super(value);
    }

    public static UserPhone create(String value) {
        //TODO ADD PHONE PATTERN TO CHECK IT. If value is not a phone number throw Custom exception
        return new UserPhone(value);
    }
}
