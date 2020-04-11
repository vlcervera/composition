package org.vlcervera.version.domain.model.vobject;

import lombok.Getter;

public abstract class ValueObject<E> {

    @Getter
    private E value;

    public ValueObject(E value) {
        this.value = value;
    }

}
