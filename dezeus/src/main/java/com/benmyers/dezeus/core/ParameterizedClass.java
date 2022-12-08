package com.benmyers.dezeus.core;

import java.util.List;
import java.util.stream.Collectors;

public class ParameterizedClass<T> {

    public final Class<T> base;
    public final List<ParameterizedClass<?>> parameters;

    public ParameterizedClass(Class<T> base, List<ParameterizedClass<?>> parameters) {
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

    public boolean fits(ParameterizedClass c) {
        // If c is just an Atom, then any parameterized class fits.
        if (c.base.equals(Atom.class)) {
            return true;
        }
        // Check that the base classes are equal.
        // Consider Conjunction<Atom, Atom> attempting to fit Disjunction<Atom, Atom>.
        if (!base.equals(c.base)) {
            return false;
        }
        // Check that the size of the parameter lists are equal.
        // This this is useful for checking parameterized fit of StatementGroups.
        if (parameters.size() != c.parameters.size()) {
            return false;
        }
        // For each parameter in c, check that the parameters of this class fit.
        // If any parameter does not fit, then conclude that this parameterized class
        // does not fit c.
        for (int i = 0; i < parameters.size(); i++) {
            ParameterizedClass<?> checkForFit = parameters.get(i);
            ParameterizedClass<?> parameterClass = (ParameterizedClass<?>) c.parameters.get(i);
            if (!checkForFit.fits(parameterClass)) {
                return false;
            }
        }
        return true;
    }
}
