package org.vlcervera.composition.domain.vobject;

import lombok.Getter;

public abstract class ValueObject<E> {

    @Getter
    private E value;

    public ValueObject(E value) {
        this.value = value;
    }

}
