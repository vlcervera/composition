package org.vlcervera.composition.domain.model.vobject;

public class UserEmail extends ValueObject<String> {

    private UserEmail(String value) {
        super(value);
    }

    public static UserEmail create(String value) {
        //TODO ADD EMAIL PATTERN TO CHECK IT. If value is not an email throw Custom exception
        return new UserEmail(value);
    }
}
