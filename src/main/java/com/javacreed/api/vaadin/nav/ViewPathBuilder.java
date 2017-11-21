package com.javacreed.api.vaadin.nav;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class ViewPathBuilder {

    private final String name;
    private final Map<String, String> parameters;

    public ViewPathBuilder(final String name) throws NullPointerException {
        this.name = Objects.requireNonNull(name);
        parameters = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public ViewPathBuilder param(final String name, final Number value) throws InvalidPathEntryException {
        InvalidPathEntryException.checkNotNull(name, "Parameter value cannot be null");
        return param(name, value.toString());
    }

    public ViewPathBuilder param(final String name, final String value) throws InvalidPathEntryException {
        InvalidPathEntryException.checkArgument(StringUtils.isNotBlank(name), "Parameter name cannot be blank");
        InvalidPathEntryException.checkArgument(StringUtils.isNotBlank(value), "Parameter value cannot be blank");
        InvalidPathEntryException.checkArgument(false == name.matches(".*[\\/\\|].*"),
                "Parameter name '" + name + "' cannot contain forward-slashs nor pipes");
        InvalidPathEntryException.checkArgument(false == value.matches(".*[\\/\\|].*"),
                "Parameter value '" + value + "' cannot contain forward-slashs nor pipes");

        parameters.put(name, value);
        return this;
    }

    public String path() {
        if (parameters.isEmpty()) {
            return name;
        }

        final StringBuilder path = new StringBuilder(name);
        for (final Entry<String, String> entry : parameters.entrySet()) {
            path.append("/").append(entry.getKey());
            path.append("|").append(entry.getValue());
        }
        return path.toString();
    }

    @Override
    public String toString() {
        return path();
    }
}
