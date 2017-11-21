package com.javacreed.api.vaadin.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.javacreed.api.vaadin.nav.ViewPathParameters.Parameter;

public class ViewPathParameters implements Iterable<Parameter> {

    public static class Parameter {
        private final String name;
        private final String value;

        public Parameter(final String name) {
            this(name, null);
        }

        public Parameter(final String name, final String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Parameter other = (Parameter) obj;
            if (name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!name.equals(other.name)) {
                return false;
            }
            if (value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!value.equals(other.value)) {
                return false;
            }
            return true;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (value == null ? 0 : value.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "Parameter [name=" + name + ", value=" + value + "]";
        }
    }

    public static ViewPathParameters parse(final String parameters) {
        final List<Parameter> map = new ArrayList<>();

        // TODO: add validations
        final String[] parts = parameters.split("\\/");
        for (final String part : parts) {
            final String[] pair = part.split("\\|", 2);
            map.add(pair.length == 2 ? new Parameter(pair[0], pair[1]) : new Parameter(pair[0]));
        }

        return new ViewPathParameters(Collections.unmodifiableList(map));
    }

    private final List<Parameter> parameters;

    private ViewPathParameters(final List<Parameter> parameters) throws NullPointerException {
        this.parameters = Objects.requireNonNull(parameters);
    }

    @Override
    public Iterator<Parameter> iterator() {
        return parameters.iterator();
    }

    public ViewPathParameters perform(final Predicate<String> predicate, final Consumer<Parameter> function) {
        for (final Parameter p : this) {
            if (predicate.test(p.getName())) {
                function.accept(p);
                break;
            }
        }
        return this;
    }

    public ViewPathParameters perform(final String name, final Consumer<Parameter> function) {
        return perform(p -> name.equalsIgnoreCase(p), function);
    }
}
