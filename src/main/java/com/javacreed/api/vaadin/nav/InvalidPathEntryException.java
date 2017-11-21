package com.javacreed.api.vaadin.nav;

public class InvalidPathEntryException extends RuntimeException {

    private static final long serialVersionUID = -6377778218114402079L;

    public static void checkArgument(final boolean argument, final String errorMessage) throws InvalidPathEntryException {
        if (false == argument) {
            throw new InvalidPathEntryException(errorMessage);
        }
    }

    public static <T> T checkNotNull(final T argument, final String errorMessage) throws InvalidPathEntryException {
        if (argument == null) {
            throw new InvalidPathEntryException(errorMessage);
        }

        return argument;
    }

    public InvalidPathEntryException(final String message) {
        super(message);
    }
}
