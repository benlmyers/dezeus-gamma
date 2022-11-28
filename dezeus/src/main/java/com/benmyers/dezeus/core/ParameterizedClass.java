package com.benmyers.dezeus.core;

import java.util.List;
import java.util.stream.Collectors;

public class ParameterizedClass<T> {

    public final Class<T> base;
    public final List<?> parameters;

    public ParameterizedClass(Class<T> base, List<?> parameters) {
        this.base = base;
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParameterizedClass) {
            ParameterizedClass<?> other = (ParameterizedClass<?>) obj;
            return base.equals(other.base) && parameters.equals(other.parameters);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return base.hashCode() + parameters.hashCode();
    }

    @Override
    public String toString() {
        if (parameters.isEmpty()) {
            return base.getSimpleName();
        } else {
            return base.getSimpleName() + "<"
                    + parameters.stream().map(Object::toString).collect(Collectors.joining(", ")) + ">";
        }
    }
}
